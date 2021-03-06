/*
 * 
 *               Panbox - encryption for cloud storage 
 *      Copyright (C) 2014-2015 by Fraunhofer SIT and Sirrix AG 
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * Additonally, third party code may be provided with notices and open source
 * licenses from communities and third parties that govern the use of those
 * portions, and any licenses granted hereunder do not alter any rights and
 * obligations you may have under such open source licenses, however, the
 * disclaimer of warranty and limitation of liability provisions of the GPLv3 
 * will apply to all the product.
 * 
 */
package org.panbox.desktop.sharemgmt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.panbox.desktop.common.gui.shares.FolderPanboxShare;
import org.panbox.desktop.common.gui.shares.PanboxShare;

public abstract class AddNewShareTest extends ShareMgmtTest {
	
	@Rule
	public TemporaryFolder testFolder = new TemporaryFolder();

	@Before
	public void setUp() throws Exception {
		super.setUp();

		File test123share = testFolder.newFolder("test123share");
		if (test123share.exists()) {
			// clean test123share directory
			FileUtils.deleteDirectory(test123share);
		}
	}

	@Test
	public void test() {
		try {
			String folder = "test123share";
			String shareName = "Test123";

			File test123share = testFolder.newFolder(folder);
			test123share.mkdirs();

			PanboxShare s = new FolderPanboxShare(null,
					test123share.getAbsolutePath(), shareName, 0);
			manager.addNewShare(s, password);
			debug("added new Share");

			PanboxShare share = manager.getShareForName(shareName);

			assertEquals(shareName, share.getName());
			assertEquals(test123share.getAbsolutePath(), share.getPath());
			debug("Test successful");

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
}
