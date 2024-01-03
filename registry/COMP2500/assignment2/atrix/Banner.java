package academy.pocu.comp2500.assignment2;

public class Banner extends ApertureAdder {
    private BannerMaterial bannerMaterial;
    private OrientationType orientation;

    public Banner(BannerSize bannerSize, ShippingType shippingMethod, Color color, BannerMaterial bannerMaterial, OrientationType orientation) {
        super(shippingMethod);
        this.bannerMaterial = bannerMaterial;
        this.orientation = orientation;
        this.color = color;
        this.setSize(bannerSize);
        this.setPrice();
        this.setDisplayName();
    }

    public BannerMaterial getBannerMaterial() {
        return bannerMaterial;
    }

    public OrientationType getOrientation() {
        return orientation;
    }

    private void setPrice() {
        if (this.bannerMaterial == BannerMaterial.GLOSS) {
            if (size.getWidth() == 1000 && size.getHeight() == 500) {
                this.price = 5000;
            } else if (size.getWidth() == 1000 && size.getHeight() == 1000) {
                this.price = 5200;
            } else if (size.getWidth() == 2000 && size.getHeight() == 500) {
                this.price = 5300;
            } else if (size.getWidth() == 3000 && size.getHeight() == 1000) {
                this.price = 6000;
            }
        } else if (bannerMaterial == BannerMaterial.SCRIM) {
            if (size.getWidth() == 1000 && size.getHeight() == 500) {
                this.price = 5100;
            } else if (size.getWidth() == 1000 && size.getHeight() == 1000) {
                this.price = 5300;
            } else if (size.getWidth() == 2000 && size.getHeight() == 500) {
                this.price = 5400;
            } else if (size.getWidth() == 3000 && size.getHeight() == 1000) {
                this.price = 6100;
            }
        } else if (bannerMaterial == BannerMaterial.MESH) {
            if (size.getWidth() == 1000 && size.getHeight() == 500) {
                this.price = 5100;
            } else if (size.getWidth() == 1000 && size.getHeight() == 1000) {
                this.price = 5300;
            } else if (size.getWidth() == 2000 && size.getHeight() == 500) {
                this.price = 5400;
            } else if (size.getWidth() == 3000 && size.getHeight() == 1000) {
                this.price = 6100;
            }
        }

    }

    private void setDisplayName() {
        if (bannerMaterial == BannerMaterial.GLOSS) {
            this.displayName = "Gloss Banner (" + size.getWidth() + " mm x " + size.getHeight() + " mm)";
        } else if (bannerMaterial == BannerMaterial.MESH) {
            this.displayName = "Mesh Banner (" + size.getWidth() + " mm x " + size.getHeight() + " mm)";
        } else if (bannerMaterial == BannerMaterial.SCRIM) {
            this.displayName = "Scrim Banner (" + size.getWidth() + " mm x " + size.getHeight() + " mm)";
        }
    }

    private void setSize(BannerSize bannerSize) {
        if (bannerSize == BannerSize.BANNER_SIZE_1000_500) {
            this.size = new Size(1000, 500);
        } else if (bannerSize == BannerSize.BANNER_SIZE_1000_1000) {
            this.size = new Size(1000, 1000);
        } else if (bannerSize == BannerSize.BANNER_SIZE_2000_500) {
            this.size = new Size(2000, 500);
        } else if (bannerSize == BannerSize.BANNER_SIZE_3000_1000) {
            this.size = new Size(3000, 1000);
        }
    }
}
