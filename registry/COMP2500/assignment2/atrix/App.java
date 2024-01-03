package academy.pocu.comp2500.assignment2;

import academy.pocu.comp2500.assignment2.registry.Registry;

public class App {
    public App(Registry registry) {
        // register your classes or methods here
        registry.registerRedStampCreator("Stamp");
        registry.registerBlueStampCreator("Stamp");
        registry.registerGreenStampCreator("Stamp");
        registry.registerWallCalendarCreator("Calendar");
        registry.registerMagnetCalendarCreator("Calendar");
        registry.registerDeskCalendarCreator("Calendar");
        registry.registerLandscapeBannerCreator("Banner");
        registry.registerPortraitBannerCreator("Banner");
        registry.registerGlossBannerCreator("Banner");
        registry.registerScrimBannerCreator("Banner");
        registry.registerMeshBannerCreator("Banner");
        registry.registerLandscapeBusinessCardCreator("BusinessCard");
        registry.registerPortraitBusinessCardCreator("BusinessCard");
        registry.registerIvoryBusinessCardCreator("BusinessCard");
        registry.registerGrayBusinessCardCreator("BusinessCard");
        registry.registerWhiteBusinessCardCreator("BusinessCard");
        registry.registerLaidBusinessCardCreator("BusinessCard");
        registry.registerLinenBusinessCardCreator("BusinessCard");
        registry.registerSmoothBusinessCardCreator("BusinessCard");
        registry.registerSingleSidedBusinessCardCreator("BusinessCard");
        registry.registerDoubleSidedBusinessCardCreator("BusinessCard");
        registry.registerCartCreator("Cart");
        registry.registerProductAdder("Cart", "addProduct");
        registry.registerProductRemover("Cart", "removeProduct");
        registry.registerTotalPriceGetter("Cart", "getTotalPrice");
        registry.registerLandscapeBannerTextApertureAdder("ApertureAdder", "addAperture");
        registry.registerLandscapeBannerImageApertureAdder("ApertureAdder", "addAperture");
        registry.registerPortraitBannerTextApertureAdder("ApertureAdder", "addAperture");
        registry.registerPortraitBannerImageApertureAdder("ApertureAdder", "addAperture");
        registry.registerGlossBannerTextApertureAdder("ApertureAdder", "addAperture");
        registry.registerGlossBannerImageApertureAdder("ApertureAdder", "addAperture");
        registry.registerScrimBannerTextApertureAdder("ApertureAdder", "addAperture");
        registry.registerScrimBannerImageApertureAdder("ApertureAdder", "addAperture");
        registry.registerMeshBannerTextApertureAdder("ApertureAdder", "addAperture");
        registry.registerMeshBannerImageApertureAdder("ApertureAdder", "addAperture");
        registry.registerLandscapeBusinessCardTextApertureAdder("ApertureAdder", "addAperture");
        registry.registerLandscapeBusinessCardImageApertureAdder("ApertureAdder", "addAperture");
        registry.registerPortraitBusinessCardTextApertureAdder("ApertureAdder", "addAperture");
        registry.registerPortraitBusinessCardImageApertureAdder("ApertureAdder", "addAperture");
        registry.registerIvoryBusinessCardTextApertureAdder("ApertureAdder", "addAperture");
        registry.registerIvoryBusinessCardImageApertureAdder("ApertureAdder", "addAperture");
        registry.registerGrayBusinessCardTextApertureAdder("ApertureAdder", "addAperture");
        registry.registerGrayBusinessCardImageApertureAdder("ApertureAdder", "addAperture");
        registry.registerWhiteBusinessCardTextApertureAdder("ApertureAdder", "addAperture");
        registry.registerWhiteBusinessCardImageApertureAdder("ApertureAdder", "addAperture");
        registry.registerLaidBusinessCardTextApertureAdder("ApertureAdder", "addAperture");
        registry.registerLaidBusinessCardImageApertureAdder("ApertureAdder", "addAperture");
        registry.registerLinenBusinessCardTextApertureAdder("ApertureAdder", "addAperture");
        registry.registerLinenBusinessCardImageApertureAdder("ApertureAdder", "addAperture");
        registry.registerSmoothBusinessCardTextApertureAdder("ApertureAdder", "addAperture");
        registry.registerSmoothBusinessCardImageApertureAdder("ApertureAdder", "addAperture");
        registry.registerSingleSidedBusinessCardTextApertureAdder("ApertureAdder", "addAperture");
        registry.registerSingleSidedBusinessCardImageApertureAdder("ApertureAdder", "addAperture");
        registry.registerDoubleSidedBusinessCardTextApertureAdder("ApertureAdder", "addAperture");
        registry.registerDoubleSidedBusinessCardImageApertureAdder("ApertureAdder", "addAperture");
    }
}
