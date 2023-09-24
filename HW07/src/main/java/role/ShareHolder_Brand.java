package role;

public class ShareHolder_Brand {
    private ShareHolder shareHolder_id;
    private Brand brand_id;

    public ShareHolder_Brand(ShareHolder shareHolder_id, Brand brand_id) {
        this.shareHolder_id = shareHolder_id;
        this.brand_id = brand_id;
    }

    public ShareHolder getShareHolder_id() {
        return shareHolder_id;
    }

    public Brand getBrand_id() {
        return brand_id;
    }
}
