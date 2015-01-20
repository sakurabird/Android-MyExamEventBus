package sakurafish.com.myexameventbus;

import android.support.annotation.NonNull;

/**
 * Created by sakura on 2015/01/19.
 */
public class ReceiverEvent {
    public final String message;

    public ReceiverEvent(@NonNull String message) {
        this.message = message;
    }
}
