package sakurafish.com.myexameventbus;

import android.support.annotation.NonNull;

/**
 * Created by sakura on 2015/01/19.
 */
public class ThreadEvent {
    public final String message;

    public ThreadEvent(@NonNull String message) {
        this.message = message;
    }
}
