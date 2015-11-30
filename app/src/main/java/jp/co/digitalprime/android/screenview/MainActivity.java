package jp.co.digitalprime.android.screenview;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.io.IOException;
import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    private SurfaceView mCameraView;
    private SurfaceHolder mHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCameraView = (SurfaceView)findViewById(R.id.surfaceView1);
        mHolder = mCameraView.getHolder();
        mHolder.addCallback(new SurfaceHolderCbk());
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        // activity_main.xml にUIコンポーネントを配置する
       /* setContentView(R.layout.activity_main);

        // text_view1： activity_main.xml の TextView の id
        TextView textView = (TextView) findViewById(R.id.text_view);
        // テキストを設定
        textView.setText("Test TextView");*/

        TextView textView = (TextView)findViewById(R.id.textView);
        textView.setText("Program set text");


    }

    //test
    public class SurfaceHolderCbk implements SurfaceHolder.Callback {
        Camera camera;

        /**
         * サーフェイスが変更された時のコールバック。
         */
        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width,
                                   int height) {
            // カメラプレビューを開始
            camera.startPreview();
        }

        /**
         * サーフェイスが生成された時のコールバック
         */
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            // カメラオープン
            camera = Camera.open();
            try {
                // プレビューを表示するサーフェイスホルダーを設定
                camera.setPreviewDisplay(holder);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * サーフェイスが破棄された時のコールバック
         */
        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            // カメラプレビューを停止
            camera.stopPreview();
            camera.release();
            camera = null;
        }
    }
}