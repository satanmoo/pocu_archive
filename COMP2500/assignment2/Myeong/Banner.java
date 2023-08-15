package academy.pocu.comp2500.assignment2;

import java.util.UUID;

public class Banner extends AdvancedProduct {
    protected BannerSize bannerSize;
    protected BannerMaterial bannerMaterial;

    public Banner(UUID id,
                  ShippingOption shippingMethod,
                  Orientation orientation,
                  Color color,
                  BannerMaterial bannerMaterial,
                  BannerSize bannerSize) {
        super(id, shippingMethod);
        super.name = "Banner";
        super.orientation = orientation;
        super.color = color;

        this.bannerSize = bannerSize;
        this.bannerMaterial = bannerMaterial;

        super.size = convertSizeByBannerSize();

        setBannerDisplayName();
        updateBannerPrice();
    }


    public BannerMaterial getBannerMaterial() {
        return this.bannerMaterial;
    }

    private Size convertSizeByBannerSize() {
        Size copySize = new Size(1, 1);
        switch (this.bannerSize) {
            case ONE_AND_ONE_HALF:
                copySize.setSize(1000, 500);
                break;

            case ONE_AND_ONE:
                copySize.setSize(1000, 1000);
                break;

            case TWO_AND_ONE_HALF:
                copySize.setSize(2000, 500);
                break;

            case THREE_AND_ONE:
                copySize.setSize(3000, 1000);
                break;

            default:
                assert (false) : "Unrecognized banner size" + this.bannerSize;
                break;
        }
        return copySize;
    }

    private void updateBannerPrice() {
        int price = 5000;

        if (this.bannerMaterial == BannerMaterial.SCRIM
                || this.bannerMaterial == BannerMaterial.MESH) {
            price += 100;
        }

        switch (this.bannerSize) {
            case ONE_AND_ONE_HALF:
                break;
            case ONE_AND_ONE:
                price += 200;
                break;
            case TWO_AND_ONE_HALF:
                price += 300;
                break;
            case THREE_AND_ONE:
                price += 1000;
                break;
            default:
                assert (false) : "Unrecognized banner size" + this.bannerSize;
                break;
        }

        super.price = price;
    }

    private void setBannerDisplayName() {
        String copyDisplayName = super.name;
        switch (this.bannerSize) {
            case ONE_AND_ONE_HALF:
                copyDisplayName = copyDisplayName.concat(" (1000 mm x 500 mm)");
                break;

            case ONE_AND_ONE:
                copyDisplayName = copyDisplayName.concat(" (1000 mm x 1000 mm)");
                break;

            case TWO_AND_ONE_HALF:
                copyDisplayName = copyDisplayName.concat(" (2000 mm x 500 mm)");
                break;

            case THREE_AND_ONE:
                copyDisplayName = copyDisplayName.concat(" (3000 mm x 1000 mm)");
                break;

            default:
                assert (false) : "Unrecognized banner size" + this.bannerSize;
                break;
        }

        switch (this.bannerMaterial) {
            case GLOSS:
                copyDisplayName = "Gloss ".concat(copyDisplayName);
                break;
            case SCRIM:
                copyDisplayName = "Scrim ".concat(copyDisplayName);
                break;
            case MESH:
                copyDisplayName = "Mesh ".concat(copyDisplayName);
                break;
            default:
                assert (false) : "Unrecognized Banner Material" + this.bannerMaterial;
                break;
        }
        super.displayName = copyDisplayName;
    }
}
