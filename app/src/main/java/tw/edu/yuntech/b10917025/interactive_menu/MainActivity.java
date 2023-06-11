package tw.edu.yuntech.b10917025.interactive_menu;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private SurfaceView surfaceView;
    private int temp = 0;
    private TextView textView;
    private CameraSource cameraSource;

    private ArrayList<ArrayList> arrayLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getPermissionCamera();

        arrayLists = new ArrayList<>();
        arrayLists.add(new ArrayList<String>());
        arrayLists.add(new ArrayList<String>());
        arrayLists.add(new ArrayList<String>());
        arrayLists.add(new ArrayList<String>());

        arrayLists.get(0).add("氣泡冬瓜");
        arrayLists.get(0).add("40");
        arrayLists.get(0).add("氣泡水、冬瓜茶");
        arrayLists.get(0).add("年復一年，攤復一攤的冬瓜茶\n" +
                "是不是已經喝膩了？\n" +
                "\n" +
                "經典冬瓜茶，搭配上氣泡bobo\n" +
                "為炎熱的雲科注入一股新活力\uD83C\uDFDD\n" +
                "綿密的氣泡口感在嘴中迸發\n" +
                "帶著淡淡的冬瓜香氣與甜感\n" +
                "清爽消暑不死甜～\n" +
                "\n" +
                "加5元還能升級成氣泡冬瓜檸檬\n" +
                "顛覆你印象中的冬瓜茶\uD83E\uDD29");
        arrayLists.get(0).add("Cappuccino_cup.glb");
        arrayLists.get(0).add(Integer.toString( R.drawable.meal1));

        arrayLists.get(1).add("冬瓜特調");
        arrayLists.get(1).add("60");
        arrayLists.get(1).add("冬瓜茶、摩卡咖啡、可可粉、檸檬");
        arrayLists.get(1).add("冬瓜與咖啡的碰撞\n" +
                "產生綿密如啤酒般的泡沫\uD83C\uDF7B\n" +
                "入口帶著冬瓜香甜與淡淡的\uD83C\uDF4B酸感\n" +
                "中段是摩卡咖啡的濃郁質地\n" +
                "最後則是可可的細緻餘韻\n" +
                "這是你從未感受過的…\n" +
                "冬瓜與咖啡的協奏曲\uD83C\uDFBC");
        arrayLists.get(1).add("Cappuccino_cup.glb");
        arrayLists.get(1).add(Integer.toString( R.drawable.meal2));

        arrayLists.get(2).add("冬瓜拿鐵");
        arrayLists.get(2).add("60");
        arrayLists.get(2).add("冬瓜茶、全脂鮮乳");
        arrayLists.get(2).add("懷舊的冬瓜茶與文青必備的拿鐵\n" +
                "細細品嚐入口的甘與苦\uD83E\uDD70\n" +
                "加10元還能多一份珍珠\uD83E\uDDCB\n" +
                "為你的冬瓜拿鐵帶來更多的口感\n");
        arrayLists.get(2).add("Cappuccino_cup.glb");
        arrayLists.get(2).add(Integer.toString( R.drawable.meal3));

        arrayLists.get(3).add("珍珠咖啡紅茶");
        arrayLists.get(3).add("60");
        arrayLists.get(3).add("錫蘭紅茶、摩卡咖啡、冬瓜蜜珍珠");
        arrayLists.get(3).add("入口時迸發的咖啡與紅茶香\uD83C\uDF42\n" +
                "QQ珍珠帶著淡淡的冬瓜甜感\n" +
                "三者間的完美協調\n" +
                "喜歡口感的你不能錯過\uD83D\uDE3B");

        arrayLists.get(3).add("Cappuccino_cup.glb");
        arrayLists.get(3).add(Integer.toString( R.drawable.meal4));

        surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        textView = (TextView) findViewById(R.id.textView);

        BarcodeDetector barcodeDetector = new BarcodeDetector.Builder(this)
                .setBarcodeFormats(Barcode.QR_CODE).build();

        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {

            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(@NonNull Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> qrCodes = detections.getDetectedItems();
                if (qrCodes.size() != 0 && qrCodes.valueAt(0).displayValue.equals("dongscoffee")) {

                    Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("store", qrCodes.valueAt(0).displayValue);
                    bundle.putStringArrayList("0", arrayLists.get(0));
                    bundle.putStringArrayList("1", arrayLists.get(1));
                    bundle.putStringArrayList("2", arrayLists.get(2));
                    bundle.putStringArrayList("3", arrayLists.get(3));

                    intent.putExtras(bundle);
                    finish();
                    startActivity(intent);
                    temp = 1;

                    onDestroy();
                }
            }
        });
        cameraSource = new CameraSource.Builder(this, barcodeDetector)
                //.setRequestedPreviewSize(300, 300) // 可以自訂預覽視窗畫面內容大小
                .setAutoFocusEnabled(true) // 自動對焦
                .build();

        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED)
                    return;
                try {
                    cameraSource.start(surfaceHolder);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
                cameraSource.stop();
            }
        });
    }

    private static final int REQUEST_CAMERA_PERMISSION = 1;

    private void getPermissionCamera() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {
            // 已有相機權限，不須再詢問
            return;
        }

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {

            // 曾經被使用者拒絕授予權限過，可以在這邊提醒使用者為何需要權限
            new AlertDialog.Builder(this)
                    .setCancelable(false)
                    .setTitle("需要相機權限")
                    .setMessage("需要相機權限才能掃描 QR Code，請授予相機權限")
                    .setPositiveButton("OK", (dialog, which) -> {
                                // 再次顯示權限授予視窗
                                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
                            }
                    )
                    .show();
        } else {
            // 第一次詢問權限，或者使用者點選「不再詢問」
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
        }
    }

    /**
     * 取得詢問相機權限的結果
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_CAMERA_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 使用者同意授予權限
                    Toast.makeText(this, "已取得相機權限", Toast.LENGTH_SHORT).show();
                } else {
                    // 使用者拒絕授予權限
                    Toast.makeText(this, "未取得相機權限", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();

        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                cameraSource.release();
            }
        });

//        db.close();
    }
}