package proxy.synchronicity;

public class BlackBoxProvider {

    public static IBlackBox getBlackBox(){
        return SyncProxyWrapper.wrap(IBlackBox.class, new BlackBox());
    }
}
