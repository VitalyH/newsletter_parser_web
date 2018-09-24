package com.vitaly.h.newsletterparserweb;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParserServiceTest {

    private ParserService parserService;
    private ParserModel parserModel;

    @Before
    public void setUp() {
        parserService = new ParserService();
        parserModel = new ParserModel();
    }

    @Test
    public void dashesTest() {
        parserModel.setOriginalText("--");
        parserService.runParser(parserModel);
        assertEquals(" — \n", parserService.getOutputField());
    }

    @Test
    public void emptyLineTest() {
        parserModel.setOriginalText(" ");
        parserService.runParser(parserModel);
        assertEquals("\n", parserService.getOutputField());
    }

    @Test
    public void headerTest() {
        parserModel.setOriginalText("h==Test");
        parserService.runParser(parserModel);
        assertEquals("<div data-change=\"tr\"><div align=\"left\" class=\"title\" style=\"padding: 38px 0 28px; line-height: 36px; font-size: 31px; font-weight: 300; color: #171313!important; font-family: 'Roboto', Arial, sans-serif;\" data-change=\"td\" colspan=\"2\" valign=\"middle\">Test</div></div>\n", parserService.getOutputField());
    }

    @Test
    public void complexTest() {
        parserModel.setOriginalText("h==Test\n" +
                "\n" +
                "--\n" +
                "\n" +
                "\n" +
                "\n" +
                "«Однако»");
        parserService.runParser(parserModel);
        assertEquals("<div data-change=\"tr\"><div align=\"left\" class=\"title\" style=\"padding: 38px 0 28px; line-height: 36px; font-size: 31px; font-weight: 300; color: #171313!important; font-family: 'Roboto', Arial, sans-serif;\" data-change=\"td\" colspan=\"2\" valign=\"middle\">Test</div></div>\n" +
                "\n" +
                " — \n" +
                "\n" +
                "\n" +
                "\n" +
                "\"Однако\"\n", parserService.getOutputField());
    }

    @After
    public void generalCleaning() {
        parserService = null;
        parserModel = null;
    }
}
