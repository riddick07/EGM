package com.gipermarket.util;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parser from HTML
 * 
 * @author oleg.topchiy
 */
public class HTMLParser {
	/**
	 * Parser from HTML with tegs to text with paragraphs
	 * 
	 * @author oleg.topchiy
	 */
	public static String parse(String description) {

		if (description == null) {
			return "";
		}

		description = description.replaceAll("(<[Pp]?>)|(<[Pp] *.*?>)", "  ");
		description = description.replaceAll("(</[Pp]>)", "");

		description = formatTables(description);

		description = description.replaceAll("(<[Bb][Rr] */*>)", "\r\n");

		description = formatLists(description);

		Pattern stylePatern = Pattern.compile("<[Ss][Tt][Yy][Ll][Ee] *.*?>.*?(</[Ss][Tt][Yy][Ll][Ee]>)", Pattern.DOTALL);
		Matcher styleMatcher = stylePatern.matcher(description);
		while (styleMatcher.find()) {
			String style = styleMatcher.group(0);
			description = description.replace(style, "");
		}

		Pattern wordPatern = Pattern.compile("<w:WordDocument *.*?>.*?(</w:WordDocument>)", Pattern.DOTALL);
		Matcher wordMatcher = wordPatern.matcher(description);
		while (wordMatcher.find()) {
			String style = wordMatcher.group(0);
			description = description.replace(style, "");
		}

		Pattern latentPatern = Pattern.compile("<w:LatentStyles *.*?>.*?(</w:LatentStyles>)", Pattern.DOTALL);
		Matcher latentMatcher = latentPatern.matcher(description);
		while (latentMatcher.find()) {
			String style = latentMatcher.group(0);
			description = description.replace(style, "");
		}

		description = description.replaceAll("(<.*?>)", "");
		description = replaceSpecialHTMLSigns(description);

		description = description.replaceAll("(?m)^[ \t]*\r?\n", "");

		return description;
	}

	public static final String replaceSpecialHTMLSigns(String description) {
		description = description.replace("&nbsp;", " ");
		description = description.replace("&lt;", "<");
		description = description.replace("&gt;", ">");
		description = description.replace("&middot;", "�");
		description = description.replace("&ldquo;", "�");
		description = description.replace("&rsquo;", "�");
		description = description.replace("&lsquo;", "�");
		description = description.replace("&sect;", "�");

		return description;
	}

	public static final String formatLists(String description) {
		Pattern olPatern = Pattern.compile("<[Oo][Ll] *.*?>.*?(</[oO][lL]>)", Pattern.DOTALL);
		Matcher olMatcher = olPatern.matcher(description);
		while (olMatcher.find()) {
			String stringBefore = olMatcher.group(0);
			String stringAfter = stringBefore.replaceAll("(<[Oo][Ll]?>)|(<[Oo][Ll] *.*?>)", "");
			int count = 1;
			while (!stringBefore.equals(stringAfter)) {
				stringBefore = stringAfter;
				stringAfter = stringBefore.replaceFirst("(<[Ll][Ii]?>)|(<[Ll][Ii] *.*?>)", "      " + count + ") ");
				count++;
			}
			description = description.replace(olMatcher.group(0), stringAfter);
		}

		Pattern ulPatern = Pattern.compile("<[Uu][Ll] *[^/].*?>.*?(</[Uu][lL]>)", Pattern.DOTALL);
		Matcher ulMatcher = ulPatern.matcher(description);
		while (ulMatcher.find()) {
			String list = ulMatcher.group(0);
			list = list.replaceAll("(<[Ll][Ii]?>)|(<[Ll][Ii] *.*?>)", "      - ");
			description = description.replace(ulMatcher.group(0), list);
		}

		return description;
	}

	public static final String formatTables(String description) {

		Pattern tablePatern = Pattern.compile("<[Tt][Aa][Bb][Ll][Ee] *[^/].*?>.*?(</[Tt][Aa][Bb][Ll][Ee]>)", Pattern.DOTALL);
		Matcher tableMatcher = tablePatern.matcher(description);
		Map<Integer, List<TableCell>> table = new HashMap<Integer, List<TableCell>>();
		Integer maxColNum = 0;
		while (tableMatcher.find()) {
			String list = tableMatcher.group(0);
			list = list.replaceAll("(<[Bb][Rr] */*>)", "");
			list = list.replaceAll("<tbody>", "");
			list = list.replaceAll("</tbody>", "");
			list = list.replaceAll("<[Tt][Aa][Bb][Ll][Ee].*?>", "");
			list = list.replaceAll("</[Tt][Aa][Bb][Ll][Ee].*?>", "");
			list = list.replaceAll("</tr>", "");
			list = list.replaceAll("(<[Bb][Rr] */*>)", "");
			list = list.replaceAll("(?m)^[ \t]*\r?\n", "");

			for (int i = 1; i < list.split("<[Tt][Rr].*?>").length; i++) {
				int colNum = 0;
				table.put(i, new LinkedList<TableCell>());
				String[] cells = list.split("<[Tt][Rr].*?>")[i].split("</[Tt][Dd]>");
				String row = "";

				for (int k = 0; k < cells.length; k++) {
					int colspan = 1;
					int rowspan = 1;
					Pattern colspanPatern = Pattern.compile("[Cc][Oo][Ll][Ss][Pp][Aa][Nn]=[\"\'][0-9]*?[\"\']");
					Matcher colspanMatcher = colspanPatern.matcher(cells[k]);

					while (colspanMatcher.find()) {
						String span = colspanMatcher.group(0);
						span = span.replaceAll("[^0-9]", "");
						colspan = Integer.parseInt(span);
					}

					Pattern rowspanPatern = Pattern.compile("[Rr][Oo][Ww][Ss][Pp][Aa][Nn]=[\"\'][0-9]*?[\"\']");
					Matcher rowspanMatcher = rowspanPatern.matcher(cells[k]);

					while (rowspanMatcher.find()) {
						String span = rowspanMatcher.group(0);
						span = span.replaceAll("[^0-9]", "");
						rowspan = Integer.parseInt(span);
					}

					cells[k] = cells[k].replaceAll("(<.*?>)", "");
					cells[k] = cells[k].trim();
					row = row + cells[k];
					table.get(i).add(new TableCell(cells[k], rowspan, colspan, cells[k].length()));
					colNum = colNum + colspan;
					colspan = 1;
					rowspan = 1;
				}
				if (colNum > maxColNum) {
					maxColNum = colNum;
				}
			}

			for (int i = 1; i <= table.size(); i++) {
				table = addRowspans(table, table.get(i), i);
			}

			int colsizes[] = new int[maxColNum];

			int j = 0;
			while (j < maxColNum) {
				for (int i = 1; i <= table.size(); i++) {
					List<TableCell> currentRow = table.get(i);
					if (TableCell.getSizeByRealPosition(j, currentRow) == null) {
						continue;
					}
					if (TableCell.getSizeByRealPosition(j, currentRow) > colsizes[j]) {
						colsizes[j] = TableCell.getSizeByRealPosition(j, currentRow);
					}
				}
				j++;
			}

			String result = "";
			for (int i = 1; i <= table.size(); i++) {
				table.put(i, TableCell.addSpaces(table.get(i), colsizes));
				result = result + TableCell.collapseRow(table.get(i));
			}

			description = description.replace(tableMatcher.group(0), result);
		}
		return description;
	}

	private static Map<Integer, List<TableCell>> addRowspans(Map<Integer, List<TableCell>> table, List<TableCell> row, Integer rowNum) {
		int realNum = 0;
		for (int i = 0; i < row.size(); i++) {
			realNum = realNum + row.get(i).getColspann();
			if (row.get(i).getRowspan() > 1) {
				for (int j = 1; j < row.get(i).getRowspan(); j++) {
					int currentReal = 0;
					for (int k = 0; k < table.get(rowNum + j).size(); k++) {
						currentReal = currentReal + table.get(rowNum + j).get(k).getColspann();
						if (currentReal == realNum) {
							table.get(rowNum + j).set(k, new TableCell("", 1, row.get(i).getColspann(), 0));
						}
					}
				}
			}
		}
		return table;
	}

	/**
	 * Object for TableCell
	 */
	private static class TableCell {
		private String content;
		private Integer rowspan;
		private Integer colspan;
		private Integer size;

		public TableCell(String content, Integer rowspan, Integer colspan, Integer size) {
			this.content = content;
			this.rowspan = rowspan;
			this.colspan = colspan;
			this.size = size;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public Integer getRowspan() {
			return rowspan;
		}

		public Integer getColspann() {
			return colspan;
		}

		public Integer getSize() {
			return size;
		}

		public static List<TableCell> addSpaces(List<TableCell> row, int sizes[]) {
			int currentCell = 0;
			for (int i = 0; i < row.size(); i++) {
				int size = 0;
				for (int j = 0; j < row.get(i).getColspann(); j++) {
					size = size + sizes[currentCell + j];
					if (j > 0) {
						size++;
					}
				}
				while (size > row.get(i).getContent().length()) {
					row.get(i).setContent(row.get(i).getContent() + " ");
				}
				row.get(i).setContent(row.get(i).getContent() + "|");
				currentCell = currentCell + row.get(i).getColspann();
			}
			return row;
		}

		public static Integer getSizeByRealPosition(int number, List<TableCell> row) {
			if (row.size() <= number) {
				return null;
			}
			int i = 0;
			int realNum = -1;
			while (realNum < number) {
				realNum = realNum + row.get(i).getColspann();
				if (number == realNum) {
					if (row.get(i).getColspann() < 2)
						return row.get(i).getSize();
					return null;
				}
				i++;
			}
			return null;
		}

		/**
		 * Parsing row from HTML table to text
		 * 
		 * @param row
		 * @return
		 */
		public static String collapseRow(List<TableCell> row) {
			String result = "";
			for (TableCell cell : row) {
				result = result + cell.getContent();
			}
			result = result + "\r\n";
			return result;
		}
	}
}
