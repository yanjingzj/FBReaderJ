/*
 * Copyright (C) 2007-2009 Geometer Plus <contact@geometerplus.com>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301, USA.
 */

package org.geometerplus.zlibrary.core.filesystem;

import java.util.*;
import java.io.*;

import org.geometerplus.zlibrary.core.library.ZLibrary;

public final class ZLPhysicalFile extends ZLFile {
	private final File myFile;
	
	ZLPhysicalFile(String path) {
		this(new File(path));
	}

	public ZLPhysicalFile(File file) {
		myFile = file;
		init();
	}
	
	public boolean exists() {
		return myFile.exists();
	}
	
	public long size() {
		return myFile.length();
	}	
	
	public boolean isDirectory() {
		return myFile.isDirectory();
		//return myFile.length() == 0;
	}
	
	public boolean remove() {
		return myFile.delete();
	}

	public String getPath() {
		return myFile.getPath();
	}
	
	public String getNameWithExtension() {
		return myFile.getName();
	}

	public ZLFile getParent() {
		return isDirectory() ? null : new ZLPhysicalFile(myFile.getParent());
	}

	public ZLPhysicalFile getPhysicalFile() {
		return this;
	}
    
	public InputStream getInputStream() throws IOException {
		return new FileInputStream(myFile);
	}

	protected List<ZLFile> directoryEntries() {
		File[] subFiles = myFile.listFiles();
		if ((subFiles == null) || (subFiles.length == 0)) {
			return Collections.emptyList();
		}

		ArrayList<ZLFile> entries  = new ArrayList<ZLFile>(subFiles.length);
		for (File f : subFiles) {
			entries.add(new ZLPhysicalFile(f));
		}
		return entries;
	}
}
