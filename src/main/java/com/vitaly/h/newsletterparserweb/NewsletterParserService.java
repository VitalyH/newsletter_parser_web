package com.vitaly.h.newsletterparserweb;

import org.springframework.stereotype.Service;

@Service
class NewsletterParserService {
    private String inputField;
    private String outputField;

    void runParser(ParserModel parserModel) {

        inputField = parserModel.getOriginalText();
        outputField = "";
        parseTextFromInputField();
    }

    private void parseTextFromInputField() {
        String modifiedLineOfText;
        // A code snippets for newsletter.
        String div = "</div></div>";
        String spanLi = "</span></li>";
        String spanLiUlEnd = "</span></li></ul></div>";
        String header = "<div data-change=\"tr\"><div align=\"left\" class=\"title\" style=\"padding: 38px 0 28px; line-height: 36px; font-size: 31px; font-weight: 300; color: #171313!important; font-family: 'Roboto', Arial, sans-serif;\" data-change=\"td\" colspan=\"2\" valign=\"middle\">";
        String paragraph = "<div data-change=\"tr\"><div class=\"td\" style=\"padding-bottom: 35px; color: #777777; font-size: 16px; font-weight: 300; line-height: 22px; font-family: 'Roboto', Arial, sans-serif;\" data-change=\"td\" colspan=\"2\">";
        String separator = "<div data-change=\"tr\"><div class=\"divider\" data-change=\"td\" colspan=\"2\"><div style=\"width: 333px; height: 2px; background-color: #d9d9d9;\"></div></div></div>";
        String listStart = "<div data-change=\"tr\"><div class=\"td\" style=\"padding-bottom: 35px; color: #777777; font-size: 16px; font-weight: 300; line-height: 22px; font-family: 'Roboto', Arial, sans-serif;\" data-change=\"td\" colspan=\"2\"> <ul class=\"ul\" style=\"margin: 0; padding: 0 0 0 40px\"> <li class=\"li\" style=\"color: #171313; padding: 5px 11px;\"><span style=\"color: #777777\">";
        String list = "<li class=\"li\" style=\"color: #171313; padding: 5px 11px;\"><span style=\"color: #777777\">";
        String reporter = "<div class=\"include\" data-change=\"tr\">[include]/www/htdocs/rudelfi/includes/newsletter2018/reporter.inc[/include]</div>";
        String fotoLeftStart = "<div data-change=\"tr\"><div class=\"td\" valign=\"top\" colspan=\"2\" style=\"padding-bottom: 35px; position: relative; color: #777777; font-size: 16px; font-weight: 300; line-height: 22px; font-family: 'Roboto', Arial, sans-serif; overflow:auto;\" data-change=\"td\"> <div class=\"text-first\" style=\"width: 412px; float: right;\">";
        String fotoLeftEnd = "</div><div class=\"image-first\" style=\"position: relative; width: 397px; float: left;\"><img width=\"397\" height=\"221\" style=\"display: block;\" src=\"http://placehold.it/397x221\"><div class=\"img-author\" style=\"height: 34px; padding: 0 9px; line-height: 34px; font-size: 12px; font-weight: 400; background-color: #f7f7f7; color: #616161;\">Фото: ???</div></div></div>";
        String fotoRightStart = "<div data-change=\"tr\"><div class=\"td\" style=\"padding-bottom: 35px; position: relative; color: #777777; font-size: 16px; font-weight: 300; line-height: 22px; font-family: 'Roboto', Arial, sans-serif; overflow:auto;\" data-change=\"td\" colspan=\"2\" valign=\"top\"><div class=\"text-first inverse-text\" style=\"width: 412px; float: left;\">";
        String fotoRightEnd = "</div><div class=\"image-first inverse-image\" style=\"position: relative; width: 397px; float: right;\"><img width=\"397\" height=\"221\" style=\"display: block;\" src=\"http://placehold.it/397x221\"><div class=\"img-author\" style=\"height: 34px; padding: 0 9px; line-height: 34px; font-size: 12px; font-weight: 400; background-color: #f7f7f7; color: #616161;\">Фото: ??????</div></div>";
        String signStart = "<div data-change=\"tr\"><div style=\"overflow:auto;\" data-change=\"td\" colspan=\"2\"> <img width=\"60\" height=\"60\" class=\"img-writer\" style=\"float: left; width: 60px; height: 60px; margin: 0 22px 43px 62px; display: inline-block!important; vertical-align: top;\" alt=\"name surname\" src=\"http://g1.delphi.lv/wd/f/9723/4QE6UW_vitaalijs.jpeg\"><div class=\"img-desc\" style=\"float: left; display: inline-block!important; vertical-align: top; color: #777777; font-size: 15px; font-family: 'Roboto', Arial, sans-serif;\">Ваш, <br>";
        String signEnd = "<br><b style=\"color: #171313\">Виталий Хлапковский</b></div>";
        // Bold and web-links.
        String originalBoldStart = "<strong>";
        String newBoldStart = "<b style=\"color: #171313\">";
        String originalBoldEnd = "</strong>";
        String newBoldEnd = "</b>";
        String originalWebLinkStart = "<a href=\"";
        String newWebLinkStart = "<a style=\"color: #0099ff; text-decoration: none\" href=\"";
        // Dashes and quotes.
        String shortDash = " - ";
        String extraShortDash = " – ";
        String doubleDash = "--";
        String longDash = " — ";
        String guillemetOpen = "«";
        String guillemetClose = "»";
        String strangeQuote = "„";
        String quoteToReplace = "\"";
        String crookedQuoteOpen = "“";
        String crookedQuoteClose = "”";

    // Test gg ff

        for (String lineOfTextFromInputField : inputField.trim().split("\\n")) {
            String arrayWithMarkerAndLineOfText[] = lineOfTextFromInputField.split("==");

            if (arrayWithMarkerAndLineOfText.length > 1)
                modifiedLineOfText = arrayWithMarkerAndLineOfText[1];
            else
                modifiedLineOfText = "";

            switch (arrayWithMarkerAndLineOfText[0]) {
                case "h":
                    modifiedLineOfText = header + modifiedLineOfText + div;
                    break;
                case "p":
                    modifiedLineOfText = paragraph + modifiedLineOfText + div;
                    break;
                case "s":
                    modifiedLineOfText = separator;
                    break;
                case "ls":
                    modifiedLineOfText = listStart + modifiedLineOfText + spanLi;
                    break;
                case "ll":
                    modifiedLineOfText = list + modifiedLineOfText + spanLi;
                    break;
                case "le":
                    modifiedLineOfText = list + modifiedLineOfText + spanLiUlEnd;
                    break;
                case "r":
                    modifiedLineOfText = reporter;
                    break;
                case "fl":
                    modifiedLineOfText = fotoLeftStart + modifiedLineOfText + fotoLeftEnd;
                    break;
                case "fr":
                    modifiedLineOfText = fotoRightStart + modifiedLineOfText + fotoRightEnd;
                    break;
                case "sign":
                    modifiedLineOfText = signStart + modifiedLineOfText + signEnd;
                    break;
                default:
                    modifiedLineOfText = lineOfTextFromInputField;
                    break;
            }

            modifiedLineOfText = modifiedLineOfText.replace(originalBoldStart, newBoldStart)
                    .replace(originalBoldEnd, newBoldEnd)
                    .replace(originalWebLinkStart, newWebLinkStart)
                    .replace(guillemetOpen, quoteToReplace)
                    .replace(guillemetClose, quoteToReplace)
                    .replace(crookedQuoteOpen, quoteToReplace)
                    .replace(crookedQuoteClose, quoteToReplace)
                    .replace(strangeQuote, quoteToReplace)
                    .replace(shortDash, longDash)
                    .replace(doubleDash, longDash)
                    .replace(extraShortDash, longDash);

            //StringBuilder here "just because". String concatenation would be more readable.
            //outputField = outputField + modifiedLineOfText + "\n";
            StringBuilder sb =  new StringBuilder(outputField);
            sb.append(modifiedLineOfText)
                    .append("\n");
            outputField = sb.toString();
        }
    }

    String getOutputField() {
        return outputField;
    }

    String getInputField() {
        return inputField;
    }

    void clearAll() {
        inputField = "";
        outputField = "";
    }
}
