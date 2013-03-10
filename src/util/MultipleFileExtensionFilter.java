package util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.filechooser.FileFilter;

/**
 * This is an extension of the FileFilter class for the file chooser. It allows
 * you to have multiple file extensions associated with one description. 
 * Adapted from:
 * http://www.experts-exchange.com/Programming/Languages/Java/A_351-Custom-File-Filtering-Using-Java-File-Choosers.html
 */
public class MultipleFileExtensionFilter extends FileFilter {

	private List<String> extensions;
	private String description;

	public MultipleFileExtensionFilter(String desc, String[] exts) {
		if (exts != null) {
			extensions = new ArrayList<String>();

			for (String ext : exts) {

				// Clean array of extensions to remove "."
				// and transform to lowercase.
				extensions.add(ext.replace(".", "").trim().toLowerCase());
			}
		} // No else need; null extensions handled below.

		// Using inline if syntax, use input from desc or use
		// a default value.
		// Wrap with an if statement to default as well as
		// avoid NullPointerException when using trim().
		description = (desc != null) ? desc.trim() : "Custom File List";
	}

	// Handles which files are allowed by filter.
	@Override
	public boolean accept(File f) {

		// Allow directories to be seen.
		if (f.isDirectory())
			return true;

		// exit if no extensions exist.
		if (extensions == null)
			return false;

		// Allows files with extensions specified to be seen.
		for (String ext : extensions) {
			if (f.getName().toLowerCase().endsWith("." + ext))
				return true;
		}

		// Otherwise file is not shown.
		return false;
	}

	// 'Files of Type' description
	@Override
	public String getDescription() {
		return description;
	}
}
