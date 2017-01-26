package sakurafish.com.myexameventbus;

import rx.Observable;
import rx.subjects.PublishSubject;

public class RxBus {

    private static RxBus instance;

    private PublishSubject<Object> subject = PublishSubject.create();

    public static RxBus instanceOf() {
        if (instance == null) {
            instance = new RxBus();
        }
        return instance;
    }

    public void post(Object object) {
        subject.onNext(object);
    }

    public Observable<Object> getObservable() {
        return subject;
    }
}
