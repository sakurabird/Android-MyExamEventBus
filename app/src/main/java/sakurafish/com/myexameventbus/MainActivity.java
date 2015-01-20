package sakurafish.com.myexameventbus;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import de.greenrobot.event.EventBus;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public void onStart() {
            super.onStart();
            // EventBusを登録する
            EventBus.getDefault().register(this);
        }

        @Override
        public void onStop() {
            // EventBusを登録解除する
            EventBus.getDefault().unregister(this);
            super.onStop();
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_main, container, false);
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);

            getView().findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // バックグラウンド処理を開始します
                    runThread();
                }
            });
        }

        private void runThread() {
            Timer timer = new Timer();
            timer.schedule(new TimerTask(){
                @Override
                public void run(){
                    // EventBusで通知
                    EventBus.getDefault().post(new ThreadEvent("スレッドからの通知"));
                }
            }, 3000); //3sec遅延
        }

        /**
         * スレッドからの通知を受け取る<br>
         * このメソッドはThreadEventがpostされると呼ばれる<br>
         * UIスレッド以外から呼ばれるのでスレッドモードを使用する
         */
        public void onEventMainThread(@NonNull final ThreadEvent event){
            ((TextView) getView().findViewById(R.id.TextView)).setText(event.message);
            Toast.makeText(getActivity(), event.message, Toast.LENGTH_SHORT).show();
        }

        /**
         * BroadcastReceiverからの通知を受け取る<br>
         * このメソッドはReceiverEventがpostされると呼ばれる<br>
         * UIスレッド間のやり取りなのでスレッドモードにしなくてよい
         */
        public void onEvent(@NonNull final ReceiverEvent event){
            ((TextView) getView().findViewById(R.id.TextView)).setText(event.message);
            Toast.makeText(getActivity(), event.message, Toast.LENGTH_SHORT).show();
        }
    }
}
