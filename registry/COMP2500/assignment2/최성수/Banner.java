package academy.pocu.comp2500.assignment2;

public class Banner extends ApertureProduct {
    private final BannerType bannerType;
    private final Orientation bannerOrientation;

    public Banner(BannerType bannerType, BannerSize bannerSize, Orientation bannerOrientation, RGB color) {
        super(getBannerPrice(bannerSize, bannerType), getDisplayName(bannerType, getBannerSize(bannerSize)), getBannerSize(bannerSize), color);
        this.bannerType = bannerType;
        this.bannerOrientation = bannerOrientation;
    }

    private static String getDisplayName(BannerType bannerType, Size size) {
        return String.format("%s Banner (%d mm x %d mm)", bannerType, size.getWidth(), size.getHeight());
    }

    private static Size getBannerSize(BannerSize bannerSize) {
        if (bannerSize == BannerSize.WIDTH_1000_HEIGHT_500) {
            return new Size(1000, 500);
        } else if (bannerSize == BannerSize.WIDTH_1000_HEIGHT_1000) {
            return new Size(1000, 1000);
        } else if (bannerSize == BannerSize.WIDTH_2000_HEIGHT_500) {
            return new Size(2000, 500);
        } else {
            return new Size(3000, 1000);
        }
    }

    private static int getBannerPrice(BannerSize bannerSize, BannerType bannerType) {
        int price;
        if (bannerSize == BannerSize.WIDTH_1000_HEIGHT_500) {
            price = 5000;
        } else if (bannerSize == BannerSize.WIDTH_1000_HEIGHT_1000) {
            price = 5200;
        } else if (bannerSize == BannerSize.WIDTH_2000_HEIGHT_500) {
            price = 5300;
        } else {
            price = 6000;
        }
        if (bannerType != BannerType.GLOSS) {
            price += 100;
        }
        return price;
    }

    public Orientation getBannerOrientation() {
        return bannerOrientation;
    }

    public BannerType getMaterial() {
        return bannerType;
    }
}
