package com.gipermarket.dao.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import javax.activation.MimetypesFileTypeMap;

import net.bican.wordpress.Comment;
import net.bican.wordpress.CommentCount;
import net.bican.wordpress.InvalidPostFormatException;
import net.bican.wordpress.Main;
import net.bican.wordpress.MediaObject;
import net.bican.wordpress.Page;
import net.bican.wordpress.PostAndPageStatus;
import net.bican.wordpress.Wordpress;
import net.bican.wordpress.configuration.WpCliConfiguration;

import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import redstone.xmlrpc.XmlRpcFault;

public class WPimplTest {

	/**
	 * @param args
	 * @throws MalformedURLException
	 * @throws XmlRpcFault
	 */

	@SuppressWarnings("nls")
	private static void printComments(Wordpress wp, Integer postID, String commentStatus, Integer commentOffset, Integer commentNumber)
			throws XmlRpcFault {
		List<Comment> r = wp.getComments(commentStatus, postID, commentNumber, commentOffset);
		for (Comment comment : r) {
			System.out.println("--- BEGIN COMMENT");
			System.out.println(comment);
			System.out.println("--- END COMMENT");
		}
	}

	private static void printComment(Wordpress wp, Integer commentID) throws XmlRpcFault {
		Comment r = wp.getComment(commentID);
		System.out.println(r);
	}

	@SuppressWarnings("nls")
	private static void deleteComment(Wordpress wp, int commentID) throws XmlRpcFault {
		boolean result = wp.deleteComment(commentID);
		if (result)
			System.out.println("Comment deleted.");
		else
			System.out.println("Comment not deleted");
	}

	@SuppressWarnings({ "nls", "boxing" })
	private static void editComment(Wordpress wp, String fileName, String operation) throws XmlRpcFault, FileNotFoundException,
			IOException, InvalidPostFormatException {
		Comment comment = Comment.fromFile(new File(fileName));
		System.err.println(comment.getPost_id());
		System.err.println(comment.getContent());
		if (operation.equals("newcomment")) {
			Integer r = wp.newComment(comment.getPost_id(), comment.getParent(), comment.getContent(), comment.getAuthor(),
					comment.getAuthor_url(), comment.getAuthor_email());
			System.err.println("Comment ID: " + r);
		} else if (operation.equals("editcomment")) {
			Boolean r = wp.editComment(comment);
			if (r)
				System.err.println("Comment edited.");
			else
				System.err.println("Comment not edited.");
		}
	}

	@SuppressWarnings("nls")
	private static void showCommentCount(WpCliConfiguration config, Wordpress wp) {
		Integer post_ID = getInteger("commentcount", config);
		try {
			CommentCount result = wp.getCommentsCount(post_ID);
			System.out.println(result);
		} catch (XmlRpcFault e) {
			String reason = e.getLocalizedMessage();
			System.err.println("Operation failed, reason is: " + reason);
		}
	}

	@SuppressWarnings({ "nls", "boxing" })
	private static void delete(Options options, WpCliConfiguration config, Wordpress wp, String opt, boolean deletePage) throws XmlRpcFault {
		Integer post_ID = getInteger(opt, config);
		String publish = config.getOptionValue("publish");
		if ((publish != null) && (post_ID != null)) {
			if (deletePage) {
				System.out.println(wp.deletePage(post_ID, publish));
			} else {
				System.out.println(wp.deletePost(post_ID, publish));
			}
		} else {
			showHelp(options);
		}
	}

	private static Integer getInteger(String c, WpCliConfiguration config) {
		Integer post_ID = null;
		try {
			post_ID = Integer.valueOf(config.getOptionValue(c));
		} catch (NumberFormatException e) {
			// leave it null
		}
		return post_ID;
	}

	private static void showHelp(Options options) {
		HelpFormatter help = new HelpFormatter();
		help.printHelp(" ", options);
	}

	public static void main(String[] args) {
		try {
			Options options = new Options();
			options.addOption("?", "help", false, "Print usage information");
			options.addOption("h", "url", true, "http://www.barbarris.com.ua/");
			options.addOption("u", "user", true, "admin");
			options.addOption("p", "pass", true, "DormTel095");
			options.addOption("a", "authors", false, "Get author list");
			options.addOption("s", "slug", true, "Slug for categories");
			options.addOption("pi", "parentid", true, "Parent id for categories");
			options.addOption("oi", "postid", true, "Post id for pages and posts");
			options.addOption("c", "categories", false, "Get category list");
			options.addOption("cn", "newcategory", true, "New category (uses --slug and --parentid)");
			options.addOption("cr", "deletecategory", true, "Delete category <category_id>");
			options.addOption("pg", "pages", false, "Get page list (full)");
			options.addOption("pl", "pagelist", false, "Get page list");
			options.addOption("ps", "page", true, "Get page");
			options.addOption("pn", "newpage", true, "New page from file <arg> (needs --publish)");
			options.addOption("pe", "editpage", true, "Edit page (needs --postid and --publish");
			options.addOption("pd", "deletepage", true, "Delete page (needs --publish)");
			options.addOption("l", "publish", true, "Publish status for \"new\" options");
			options.addOption("us", "userinfo", false, "Get user information");
			options.addOption("or", "recentposts", true, "Get recent posts");
			options.addOption("os", "getpost", true, "Get post");
			options.addOption("on", "newpost", true, "New post from file <arg> (needs --publish)");
			options.addOption("oe", "editpost", true, "Edit post (needs --postid and --publish");
			options.addOption("od", "deletepost", true, "Delete post (needs --publish)");
			options.addOption("sm", "supportedmethods", false, "List supported methods");
			options.addOption("st", "supportedfilters", false, "List supported text filters");
			options.addOption("mn", "newmedia", true, "New media file (uses --overwrite)");
			options.addOption("ov", "overwrite", false, "Allow overwrite in uploading new media");
			options.addOption("so", "supportedstatus", false, "Print supported page and post status values");
			options.addOption("cs", "commentstatus", false, "Print comment status names for the blog");
			options.addOption("cc", "commentcount", true, "Get comment count for a post (-1 for all posts)");
			options.addOption("ca", "newcomment", true, "New comment from file");
			options.addOption("cd", "deletecomment", true, "Delete comment");
			options.addOption("ce", "editcomment", true, "Edit comment from file");
			options.addOption("cg", "getcomment", true, "Get comment");
			options.addOption("ct", "getcomments", true, "Get comments for the post");
			options.addOption("cs", "commentstatus", true, "Comment status (for --getcomments)");
			options.addOption("co", "commentoffset", true, "Comment offset # (for --getcomments)");
			options.addOption("cm", "commentnumber", true, "Comment # (for --getcomments)");
			try {
				WpCliConfiguration config = new WpCliConfiguration(args, options, Main.class);
				if (config.hasOption("help")) {
					showHelp(options);
				} else if ((!config.hasOption("url")) || (!config.hasOption("user")) || (!config.hasOption("pass"))) {
					System.err.println("Specify --user, --pass and --url");
				} else {
					try {
						Wordpress wp = new Wordpress(config.getOptionValue("user"), config.getOptionValue("pass"),
								config.getOptionValue("url"));
						if (config.hasOption("authors")) {
						} else if (config.hasOption("categories")) {
						} else if (config.hasOption("deletecategory")) {
							Integer category_id = Integer.valueOf(config.getOptionValue("deletecategory"));
							int r = wp.deleteCategory(category_id);
							System.out.println(r);
						} else if (config.hasOption("newcategory")) {
							String slug = config.getOptionValue("slug");
							Integer parentId = getInteger("parentid", config);
							if (slug == null)
								slug = "";
							if (parentId == null)
								parentId = 0;
							System.out.println(wp.newCategory(config.getOptionValue("newcategory"), slug, parentId));
						} else if (config.hasOption("newpage")) {
							if (!config.hasOption("publish")) {
								showHelp(options);
							} else {
								System.out.println(wp.newPage(Page.fromFile(new File(config.getOptionValue("newpage"))),
										config.getOptionValue("publish")));
							}
						} else if (config.hasOption("deletepage")) {
							delete(options, config, wp, "deletepage", true);
						} else if (config.hasOption("deletepost")) {
							delete(options, config, wp, "deletepost", false);
						} else if (config.hasOption("newpost")) {
							if (!config.hasOption("publish")) {
								showHelp(options);
							} else {
								System.out.println(wp.newPost(Page.fromFile(new File(config.getOptionValue("newpost"))),
										Boolean.valueOf(config.getOptionValue("publish"))));
							}
						} else if (config.hasOption("newmedia")) {
							String fileName = config.getOptionValue("newmedia");
							File file = new File(fileName);
							String mimeType = new MimetypesFileTypeMap().getContentType(file);
							Boolean overwrite = Boolean.FALSE;
							if (config.hasOption("overwrite"))
								overwrite = Boolean.TRUE;
							MediaObject result = wp.newMediaObject(mimeType, file, overwrite);
							if (result != null) {
								System.out.println(result);
							}
						} else if (config.hasOption("supportedstatus")) {
						} else if (config.hasOption("commentstatus")) {
						} else if (config.hasOption("commentcount")) {
							showCommentCount(config, wp);
						} else if (config.hasOption("newcomment")) {
							editComment(wp, config.getOptionValue("newcomment"), "newcomment");
						} else if (config.hasOption("editcomment")) {
							editComment(wp, config.getOptionValue("editcomment"), "editcomment");
						} else if (config.hasOption("deletecomment")) {
							System.err.println(Integer.valueOf(config.getOptionValue("deletecomment")));
							deleteComment(wp, Integer.valueOf(config.getOptionValue("deletecomment")));
						} else if (config.hasOption("getcomment")) {
							printComment(wp, Integer.valueOf(config.getOptionValue("getcomment")));
						} else if (config.hasOption("getcomments")) {
							Integer postID = Integer.valueOf(config.getOptionValue("getcomments"));
							String commentStatus = config.getOptionValue("commentstatus");
							Integer commentOffset;
							try {
								commentOffset = Integer.valueOf(config.getOptionValue("commentoffset"));
							} catch (NumberFormatException e) {
								commentOffset = null;
							}
							Integer commentNumber;
							try {
								commentNumber = Integer.valueOf(config.getOptionValue("commentnumber"));
							} catch (Exception e) {
								commentNumber = null;
							}
							printComments(wp, postID, commentStatus, commentOffset, commentNumber);
						} else {
							showHelp(options);
						}
					} catch (MalformedURLException e) {
						System.err
								.println("URL \"" + config.getOptionValue("url") + "\" is invalid, reason is: " + e.getLocalizedMessage());
					} catch (IOException e) {
						System.err.println("Can't read from file, reason is: " + e.getLocalizedMessage());
					} catch (InvalidPostFormatException e) {
						System.err.println("Input format is invalid.");
					}
				}
			} catch (ParseException e) {
				System.err.println("Can't process command line arguments, reason is: " + e.getLocalizedMessage());
			}
		} catch (XmlRpcFault e) {
			String reason = e.getLocalizedMessage();
			System.err.println("Operation failed, reason is: " + reason);
		}

	}
}
