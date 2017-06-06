/*
 * Catroid: An on-device visual programming system for Android devices
 * Copyright (C) 2010-2017 The Catrobat Team
 * (<http://developer.catrobat.org/credits>)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * An additional term exception under section 7 of the GNU Affero
 * General Public License, version 3, is available at
 * http://developer.catrobat.org/license_additional_term
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.catrobat.catroid.uiespresso.content.brick;

import org.catrobat.catroid.R;
import org.catrobat.catroid.content.bricks.SetSizeToBrick;
import org.catrobat.catroid.formulaeditor.InterpretationException;
import org.catrobat.catroid.ui.ScriptActivity;
import org.catrobat.catroid.uiespresso.content.brick.utils.BrickTestUtils;
import org.catrobat.catroid.uiespresso.util.BaseActivityInstrumentationRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.catrobat.catroid.uiespresso.content.brick.utils.BrickDataInteractionWrapper.onBrickAtPosition;

public class SetSizeToBrickTest {
	private int brickPosition;

	@Rule
	public BaseActivityInstrumentationRule<ScriptActivity> baseActivityTestRule = new
			BaseActivityInstrumentationRule<>(ScriptActivity.class, true, false);

	@Before
	public void setUp() throws Exception {
		final double newSize = 200;
		BrickTestUtils.createProjectAndGetStartScript("setSizeBrickBasicTest")
				.addBrick(new SetSizeToBrick(newSize));
		brickPosition = 1;
		baseActivityTestRule.launchActivity(null);
	}

	@Test
	public void setSizeToBrickTest() throws InterpretationException {
		int sizeValue = 20;

		onBrickAtPosition(0).checkShowsText("When program starts");
		onBrickAtPosition(brickPosition).checkShowsText("Set size to");

		onBrickAtPosition(brickPosition).onFormulaTextFiled(R.id.brick_set_size_to_edit_text)
				.performEnterNumber(sizeValue)
				.checkShowsNumber(sizeValue);
	}
}
