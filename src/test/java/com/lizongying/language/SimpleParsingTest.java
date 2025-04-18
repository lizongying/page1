// Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.

package com.lizongying.language;

import com.intellij.testFramework.ParsingTestCase;

public class SimpleParsingTest extends ParsingTestCase {

  public SimpleParsingTest() {
    super("", "p1", new P1ParserDefinition());
  }

  public void testParsingTestData() {
    doTest(true);
  }

  /**
   * @return path to test data file directory relative to root of this module.
   */
  @Override
  protected String getTestDataPath() {
    return "src/test/testData";
  }

  @Override
  protected boolean includeRanges() {
    return true;
  }

}
