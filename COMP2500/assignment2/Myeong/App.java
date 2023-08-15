package academy.pocu.comp2500.assignment2;

import academy.pocu.comp2500.assignment2.registry.Registry;

public class App {
    public App(Registry registry) {
//        registerRedStampCreator(): 빨강 잉크 스탬프를 만드는 생성자나 메서드를 등록한다. 2개의 오버로딩된 메서드가 있으니 본인의 설계에 적합한 버전을 사용할 것.
        registry.registerRedStampCreator("Stamp");
//        registerBlueStampCreator(): 파랑 잉크 스탬프를 만드는 생성자나 메서드를 등록한다. 2개의 오버로딩된 메서드가 있으니 본인의 설계에 적합한 버전을 사용할 것.
        registry.registerBlueStampCreator("Stamp");
//        registerGreenStampCreator(): 녹색 잉크 스탬프를 만드는 생성자나 메서드를 등록한다. 2개의 오버로딩된 메서드가 있으니 본인의 설계에 적합한 버전을 사용할 것.
        registry.registerGreenStampCreator("Stamp");
//        registerWallCalendarCreator(): 벽걸이 달력을 만드는 생성자나 메서드를 등록한다. 2개의 오버로딩된 메서드가 있으니 본인의 설계에 적합한 버전을 사용할 것.
        registry.registerWallCalendarCreator("Calendar");
//        registerMagnetCalendarCreator(): 자석 달력을 만드는 생성자나 메서드를 등록한다. 2개의 오버로딩된 메서드가 있으니 본인의 설계에 적합한 버전을 사용할 것.
        registry.registerMagnetCalendarCreator("Calendar");
//        registerDeskCalendarCreator(): 탁상 달력을 만드는 생성자나 메서드를 등록한다. 2개의 오버로딩된 메서드가 있으니 본인의 설계에 적합한 버전을 사용
        registry.registerDeskCalendarCreator("Calendar");
//        registerLandscapeBannerCreator(): 가로 방향 배너를 만드는 생성자나 메서드를 등록한다. 2개의 오버로딩된 메서드가 있으니 본인의 설계에 적합한 버전을 사용할 것.
        registry.registerLandscapeBannerCreator("Banner");
//        registerPortraitBannerCreator(): 세로 방향 배너를 만드는 생성자나 메서드를 등록한다. 2개의 오버로딩된 메서드가 있으니 본인의 설계에 적합한 버전을 사용할 것.
        registry.registerPortraitBannerCreator("Banner");
//        registerGlossBannerCreator(): 반사 배너를 만드는 생성자나 메서드를 등록한다. 2개의 오버로딩된 메서드가 있으니 본인의 설계에 적합한 버전을 사용할 것.
        registry.registerGlossBannerCreator("Banner");
//        registerScrimBannerCreator(): 스크림 배너를 만드는 생성자나 메서드를 등록한다. 2개의 오버로딩된 메서드가 있으니 본인의 설계에 적합한 버전을 사용할 것.
        registry.registerScrimBannerCreator("Banner");
//        registerMeshBannerCreator(): 메쉬 배너를 만드는 생성자나 메서드를 등록한다. 2개의 오버로딩된 메서드가 있으니 본인의 설계에 적합한 버전을 사용할 것.
        registry.registerMeshBannerCreator("Banner");
//        registerLandscapeBusinessCardCreator(): 가로 방향 명함을 만드는 생성자나 메서드를 등록한다. 2개의 오버로딩된 메서드가 있으니 본인의 설계에 적합한 버전을 사용할 것.
        registry.registerLandscapeBusinessCardCreator("BusinessCard");
//        registerPortraitBusinessCardCreator(): 세로 방향 명함을 만드는 생성자나 메서드를 등록한다. 2개의 오버로딩된 메서드가 있으니 본인의 설계에 적합한 버전을 사용할 것.
        registry.registerPortraitBusinessCardCreator("BusinessCard");
//        registerIvoryBusinessCardCreator(): 아이보리 종이에 찍는 명함을 만드는 생성자나 메서드를 등록한다. 2개의 오버로딩된 메서드가 있으니 본인의 설계에 적합한 버전을 사용할 것.
        registry.registerIvoryBusinessCardCreator("BusinessCard");
//        registerGrayBusinessCardCreator(): 회색 종이에 찍는 명함을 만드는 생성자나 메서드를 등록한다. 2개의 오버로딩된 메서드가 있으니 본인의 설계에 적합한 버전을 사용할 것.
        registry.registerGrayBusinessCardCreator("BusinessCard");
//        registerWhiteBusinessCardCreator(): 흰색 종이에 찍는 명함을 만드는 생성자나 메서드를 등록한다. 2개의 오버로딩된 메서드가 있으니 본인의 설계에 적합한 버전을 사용할 것.
        registry.registerWhiteBusinessCardCreator("BusinessCard");
//        registerLaidBusinessCardCreator(): 레이드지 인쇄용지에 찍는 명함을 만드는 생성자나 메서드를 등록한다. 2개의 오버로딩된 메서드가 있으니 본인의 설계에 적합한 버전을 사용할 것.
        registry.registerLaidBusinessCardCreator("BusinessCard");
//        registerLinenBusinessCardCreator(): 린넨커버 인쇄용지에 찍는 명함을 만드는 생성자나 메서드를 등록한다. 2개의 오버로딩된 메서드가 있으니 본인의 설계에 적합한 버전을 사용할 것.
        registry.registerLinenBusinessCardCreator("BusinessCard");
//        registerSmoothBusinessCardCreator(): 스노우지 인쇄용지에 찍는 명함을 만드는 생성자나 메서드를 등록한다. 2개의 오버로딩된 메서드가 있으니 본인의 설계에 적합한 버전을 사용할 것.
        registry.registerSmoothBusinessCardCreator("BusinessCard");
//        registerSingleSidedBusinessCardCreator(): 단면 명함을 만드는 생성자나 메서드를 등록한다. 2개의 오버로딩된 메서드가 있으니 본인의 설계에 적합한 버전을 사용할 것.
        registry.registerSingleSidedBusinessCardCreator("BusinessCard");
//        registerDoubleSidedBusinessCardCreator(): 양면 명함을 만드는 생성자나 메서드를 등록한다. 2개의 오버로딩된 메서드가 있으니 본인의 설계에 적합한 버전을 사용할 것.
        registry.registerDoubleSidedBusinessCardCreator("BusinessCard");
//        registerCartCreator(): 장바구니를 만드는 생성자를 등록한다.
        registry.registerCartCreator("Cart");
//        registerProductAdder(): 장바구니에 상품을 추가하는 메서드를 등록한다.
        registry.registerProductAdder("Cart", "addProduct");
//        registerProductRemover(): 장바구니에서 상품을 제거하는 메서드를 등록한다.
        registry.registerProductRemover("Cart", "removeProduct");
//        registerTotalPriceGetter(): 장바구니에서 총액을 구해오는 메서드를 등록한다.
        registry.registerTotalPriceGetter("Cart", "getTotalPrice");
//        registerLandscapeBannerTextApertureAdder(): 가로 방향 배너에 문구를 추가하는 메서드를 등록한다.
        registry.registerLandscapeBannerTextApertureAdder("AdvancedProduct", "addAperture");
//        registerLandscapeBannerImageApertureAdder(): 가로 방향 배너에 사진을 추가하는 메서드를 등록한다.
        registry.registerLandscapeBannerImageApertureAdder("AdvancedProduct", "addAperture");
//        registerPortraitBannerTextApertureAdder(): 세로 방향 배너에 문구를 추가하는 메서드를 등록한다.
        registry.registerPortraitBannerTextApertureAdder("AdvancedProduct", "addAperture");
//        registerPortraitBannerImageApertureAdder(): 세로 방향 배너에 사진을 추가하는 메서드를 등록한다.
        registry.registerPortraitBannerImageApertureAdder("AdvancedProduct", "addAperture");
//        registerGlossBannerTextApertureAdder(): 반사 배너에 문구를 추가하는 메서드를 등록한다.
        registry.registerGlossBannerTextApertureAdder("AdvancedProduct", "addAperture");
//        registerGlossBannerImageApertureAdder(): 반사 배너에 사진을 추가하는 메서드를 등록한다.
        registry.registerGlossBannerImageApertureAdder("AdvancedProduct", "addAperture");
//        registerScrimBannerTextApertureAdder(): 스크림 배너에 문구를 추가하는 메서드를 등록한다.
        registry.registerScrimBannerTextApertureAdder("AdvancedProduct", "addAperture");
//        registerScrimBannerImageApertureAdder(): 스크림 배너에 사진을 추가하는 메서드를 등록한다.
        registry.registerScrimBannerImageApertureAdder("AdvancedProduct", "addAperture");
//        registerMeshBannerTextApertureAdder(): 메쉬 배너에 문구를 추가하는 메서드를 등록한다.
        registry.registerMeshBannerTextApertureAdder("AdvancedProduct", "addAperture");
//        registerMeshBannerImageApertureAdder(): 메쉬 배너에 사진을 추가하는 메서드를 등록한다.
        registry.registerMeshBannerImageApertureAdder("AdvancedProduct", "addAperture");
//        registerLandscapeBusinessCardTextApertureAdder(): 가로 방향 명함에 문구를 추가하는 메서드를 등록한다.
        registry.registerLandscapeBusinessCardTextApertureAdder("AdvancedProduct", "addAperture");
//        registerLandscapeBusinessCardImageApertureAdder(): 가로 방향 명함에 사진을 추가하는 메서드를 등록한다.
        registry.registerLandscapeBusinessCardImageApertureAdder("AdvancedProduct", "addAperture");
//        registerPortraitBusinessCardTextApertureAdder(): 세로 방향 명함에 문구를 추가하는 메서드를 등록한다.
        registry.registerPortraitBusinessCardTextApertureAdder("AdvancedProduct", "addAperture");
//        registerPortraitBusinessCardImageApertureAdder(): 세로 방향 명함에 사진을 추가하는 메서드를 등록한다.
        registry.registerPortraitBusinessCardImageApertureAdder("AdvancedProduct", "addAperture");
//        registerIvoryBusinessCardTextApertureAdder(): 아이보리 종이에 찍는 명함에 문구를 추가하는 메서드를 등록한다.
        registry.registerIvoryBusinessCardTextApertureAdder("AdvancedProduct", "addAperture");
//        registerIvoryBusinessCardImageApertureAdder(): 아이보리 종이에 찍는 명함에 사진을 추가하는 메서드를 등록한다.
        registry.registerIvoryBusinessCardImageApertureAdder("AdvancedProduct", "addAperture");
//        registerGrayBusinessCardTextApertureAdder(): 회색 종이에 찍는 명함에 문구를 추가하는 메서드를 등록한다.
        registry.registerGrayBusinessCardTextApertureAdder("AdvancedProduct", "addAperture");
//        registerGrayBusinessCardImageApertureAdder(): 회색 종이에 찍는 명함에 사진을 추가하는 메서드를 등록한다.
        registry.registerGrayBusinessCardImageApertureAdder("AdvancedProduct", "addAperture");
//        registerWhiteBusinessCardTextApertureAdder(): 흰색 종이에 찍는 명함에 문구를 추가하는 메서드를 등록한다.
        registry.registerWhiteBusinessCardTextApertureAdder("AdvancedProduct", "addAperture");
//        registerWhiteBusinessCardImageApertureAdder(): 흰색 종이에 찍는 명함에 사진을 추가하는 메서드를 등록한다.
        registry.registerWhiteBusinessCardImageApertureAdder("AdvancedProduct", "addAperture");
//        registerLaidBusinessCardTextApertureAdder(): 레이드지 인쇄용지에 찍는 명함에 문구를 추가하는 메서드를 등록한다.
        registry.registerLaidBusinessCardTextApertureAdder("AdvancedProduct", "addAperture");
//        registerLaidBusinessCardImageApertureAdder(): 레이드지 인쇄용지에 찍는 명함에 사진을 추가하는 메서드를 등록한다.
        registry.registerLaidBusinessCardImageApertureAdder("AdvancedProduct", "addAperture");
//        registerLinenBusinessCardTextApertureAdder(): 린넨커버 인쇄용지에 찍는 명함에 문구를 추가하는 메서드를 등록한다.
        registry.registerLinenBusinessCardTextApertureAdder("AdvancedProduct", "addAperture");
//        registerLinenBusinessCardImageApertureAdder(): 린넨커버 인쇄용지에 찍는 명함에 사진을 추가하는 메서드를 등록한다.
        registry.registerLinenBusinessCardImageApertureAdder("AdvancedProduct", "addAperture");
//        registerSmoothBusinessCardTextApertureAdder(): 스노우지 인쇄용지에 찍는 명함에 문구를 추가하는 메서드를 등록한다.
        registry.registerSmoothBusinessCardTextApertureAdder("AdvancedProduct", "addAperture");
//        registerSmoothBusinessCardImageApertureAdder(): 스노우지 인쇄용지에 찍는 명함에 사진을 추가하는 메서드를 등록한다.
        registry.registerSmoothBusinessCardImageApertureAdder("AdvancedProduct", "addAperture");
//        registerSingleSidedBusinessCardTextApertureAdder(): 단면 명함에 문구를 추가하는 메서드를 등록한다.
        registry.registerSingleSidedBusinessCardTextApertureAdder("AdvancedProduct", "addAperture");
//        registerSingleSidedBusinessCardImageApertureAdder(): 단면 명함에 사진을 추가하는 메서드를 등록한다.
        registry.registerSingleSidedBusinessCardImageApertureAdder("AdvancedProduct", "addAperture");
//        registerDoubleSidedBusinessCardTextApertureAdder(): 양면 명함에 문구를 추가하는 메서드를 등록한다.
        registry.registerDoubleSidedBusinessCardTextApertureAdder("AdvancedProduct", "addAperture");
//        registerDoubleSidedBusinessCardImageApertureAdder(): 양면 명함에 사진을 추가하는 메서드를 등록한다.
        registry.registerDoubleSidedBusinessCardImageApertureAdder("AdvancedProduct", "addAperture");
    }
}
