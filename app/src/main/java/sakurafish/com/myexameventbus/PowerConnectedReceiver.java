package sakurafish.com.myexameventbus;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by sakura on 2014/10/13.
 */
public class PowerConnectedReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, final Intent intent) {
        // BroadcastReceiverから使えるか試すコード(充電ケーブルを端末に指すとテストできます)
        final String action = intent.getAction();
        if (action.equals(Intent.ACTION_POWER_CONNECTED)) {
            RxBus.instanceOf().post(new ReceiverEvent("BroadcastReceiverからの通知"));
        }
    }
}