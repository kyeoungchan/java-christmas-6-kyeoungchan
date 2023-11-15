package christmas;

import christmas.controller.ChristmasController;
import christmas.domain.BadgeGiver;
import christmas.domain.MenuOrderAssembler;
import christmas.exception.ValidatingLoopTemplate;
import christmas.service.BenefitCalculator;
import christmas.service.ChristmasService;
import christmas.util.InputValidator;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Configuration {
    public static ChristmasController getChristmasController() {
        MenuOrderAssembler menuOrderAssembler = getMenuOrderAssembler();
        InputValidator inputValidator = getInputValidator(menuOrderAssembler);
        InputView inputView = getInputView(inputValidator);
        OutputView outputView = getOutputView();
        ValidatingLoopTemplate validatingTemplate = getTemplate();
        BenefitCalculator benefitCalculator = getBenefitCalculator();
        BadgeGiver badgeGiver = getBadgeGiver();
        ChristmasService christmasService = getChristmasService(benefitCalculator, badgeGiver);
        return new ChristmasController(inputView, outputView, validatingTemplate, christmasService);
    }

    private static ChristmasService getChristmasService(
            BenefitCalculator benefitCalculator, BadgeGiver badgeGiver) {
        return new ChristmasService(benefitCalculator, badgeGiver);
    }

    private static BadgeGiver getBadgeGiver() {
        return new BadgeGiver();
    }

    private static BenefitCalculator getBenefitCalculator() {
        return new BenefitCalculator();
    }

    private static ValidatingLoopTemplate getTemplate() {
        return new ValidatingLoopTemplate();
    }

    private static OutputView getOutputView() {
        return new OutputView();
    }

    private static InputView getInputView(InputValidator inputValidator) {
        return new InputView(inputValidator);
    }

    private static InputValidator getInputValidator(MenuOrderAssembler menuOrderAssembler) {
        return new InputValidator(menuOrderAssembler);
    }

    private static MenuOrderAssembler getMenuOrderAssembler() {
        return new MenuOrderAssembler();
    }
}
