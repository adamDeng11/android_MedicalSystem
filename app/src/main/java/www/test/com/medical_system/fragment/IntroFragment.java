package www.test.com.medical_system.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import www.test.com.medical_system.R;

/*医院介绍*/
public class IntroFragment extends Fragment implements View.OnClickListener {

    public Context context;

    public IntroFragment() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_intro, container, false);
        TextView tv = view.findViewById(R.id.tv);
        ImageView iv = view.findViewById(R.id.iv);
        int type = getArguments().getInt("type");
        switch (type) {
            case 0:
                tv.setVisibility(View.VISIBLE);
                break;
            case 1:
                iv.setVisibility(View.VISIBLE);
                iv.setImageResource(R.mipmap.img_get_pill);
                break;
            case 2:
                iv.setVisibility(View.VISIBLE);
                iv.setImageResource(R.mipmap.img_);
                break;
            case 3:
                iv.setVisibility(View.VISIBLE);
                iv.setImageResource(R.mipmap.img_parking);
                break;
            case 4:
                tv.setVisibility(View.VISIBLE);
                tv.setText("1.1内科\n" +
                        "陈一\n" +
                        "硕士生导师，中华医学会呼吸分会呼吸治疗学组委员。长期从事呼吸系统疾病的临床和科研工作，对呼吸系统各类常见病、多发病及疑难重症均有丰富的临床经验，对慢性咳嗽、哮喘、慢性阻塞性肺疾病的诊治有丰富经验，对擅长对肺癌、肺部感染的诊疗。\n" +
                        "陈二\n" +
                        "副教授、副主任医师，硕士生导师、德国波恩大学医学博士、博士后。从事消化科临床工作十余年，在胃肠道疾病的诊治方面有一定造诣，尤其擅长内镜下消化道早期肿瘤的诊断和治疗。擅长各种肝炎、肝硬化和肝癌的诊断和治疗。\n" +
                        "陈三\n" +
                        "中国医师协会心内科医师分会委员。获医学博士学位后继续留校从事冠心病相关的临床与基础研究。长期从事心内科医疗、教学及科研工作，对心血管疑难、重症疾病的诊治具有丰富的临床经验和娴熟的技能，尤其在心血管疾病介入治疗领域有深厚造诣。\n" +
                        "陈四\n" +
                        "博士生导师。多次承担国家自然科学基金项目、卫生部泌尿系统疾病重大项目等，曾获中华医学科技奖一等奖。在原发性和继发性肾小球疾病、尿路感染以及慢性肾脏病、血液净化方面有较深的造诣和丰富的临床经验。\n" +
                        "\n1.2 外科\n" +
                        "赵一\n" +
                        "医学博士，博士生导师。湖北省医师协会神经外科医师分会副主任委员。有丰富的神经外科临床经验和扎实的临床工作基础，能对各类神经外科复杂、危重病例做出准确、有效诊断及处理。率先在湖北省内开了展了岩斜坡脑膜瘤、高颈段超长型脊髓髓内肿瘤和脑干肿瘤显微\n" +
                        "赵二\n" +
                        "医学博士，博士生导师。曾赴美国波士顿大学医学院UMASS医学中心进行临床工作和研究工作。主要擅长脊柱疾病的诊治，尤其擅长脊柱肿瘤等的切除及脊柱重建等治疗，具有较高的造诣。\n" +
                        "赵三\n" +
                        "医学博士、硕士生导师。擅长复杂性结石的微创治疗。近5年完成泌尿系结石微创手术3000余例。现为中华医学会县级医院人才培养计划结石培训项目负责人兼培训导师。获邀在亚太及全国泌尿外科年会以及多个省市进行结石手术演示。\n" +
                        "赵四\n" +
                        "主任医师。胆胰外科副主任。现任湖北省医学会腔镜学会副主任委员，武汉市医学会腔镜学会副主任委员。从事普通外科近40年，对肝脏疾病、疑难胆道和胰腺疾病的诊治有较高造诣。\n" +
                        "\n1.3 儿科\n" +
                        "张一\n" +
                        "博士生导师。重点研究小儿消化道畸形的发生、发展及治疗。现从事小儿外科临床工作已二十五年余，擅长先天性巨结肠、胆总管囊肿以及直肠肛门畸形等诊断与治疗。\n" +
                        "张二\n" +
                        "医学博士。擅长儿科疾病诊治，尤其擅长新生儿疑难病和危重病的抢救治疗、提高极小早产儿的存活率、早产儿及高危新生儿的随访等。\n" +
                        "\n1.4 皮肤科\n" +
                        "李一\n" +
                        "主任医师、博士研究生导师。擅长白癜风、银屑病、性病、疑难性或遗传性皮肤病的诊断与治疗。\n" +
                        "李二\n" +
                        "医学博士。长期从事皮肤病性病科的临床，教学及科研工作。现对皮肤病尤其是疑难危重疾病的诊治具有较丰富的临床经验；对尖锐湿疣复发的防治及各种特殊类型梅毒的诊治具有较深入的研究。\n" +
                        "\n1.5 中西医结合科\n" +
                        "唐一\n" +
                        "主任医师，教授。上世纪六十年代毕业后，后成为我国首批西学中医生，调入中医科从事中西医结合工作。临床经验丰富，善于沟通中、西医之间的隔阂，扬中、西医之所长，避中、西医之所短。\n" +
                        "唐二\n" +
                        "主任医师，博士生导师。擅长中西医结合内科常见疾病及疑难杂症的诊治，尤其在内分泌代谢性疾病、肾脏病及老年相关性疾病等疾病的研究和治疗方面取得成就。\n" +
                        "\n1.6 眼科\n" +
                        "林一\n" +
                        "亚太眼整形终身会员，国内著名泪道及眼整形专家。对各种类型上睑下垂、内眦赘皮、倒睫、眼窝凹陷义眼台植入、眼睑缺损修复以及双重睑、眼袋、开眼角、眼周注射等眼部年轻化手术经验丰富。\n" +
                        "林二\n" +
                        "主任医师。长期从事眼科教学、科研、临床医疗工作，有丰富的临床实践经验，特别在眼科影像诊断技术上有一定的专长。擅长眼部肿瘤的诊治、眼科影像诊断及白内障的治疗。\n" +
                        "\n" +
                        "\n1.7 耳鼻喉科\n" +
                        "郑一\n" +
                        "博士生导师，中华耳鼻喉学会武汉分会会员。专心从事耳鼻咽喉科临床及临床基础研究，着重参与鼻咽恶性肿瘤的临床分子生物学特性及变应性鼻炎发病机制的研究，并取得一定成果。\n" +
                        "郑二\n" +
                        "医学博士。对耳鼻咽喉头颈外科的常见病、多发病和急危重症的诊断和处理有丰富的临床经验。目前主要的研究方向为“耳聋、耳鸣发病机制和药物治疗”、“阻塞性睡眠呼吸暂停综合征的高危诊断及个体化治疗”和“嗓音疾病诊治的系统评估”。 \n" +
                        "\n1.8 口腔科\n" +
                        "叶一\n" +
                        "中华口腔医学会正畸委员会常务委员。从事口腔正畸学临床、教学和科研工作30年。擅长儿童反合、偏颌、开合、埋伏牙以及各类青少年和成人错颌畸形的矫治。\n" +
                        "叶二\n" +
                        "毕业于北京医学院口腔系，任武汉市口腔医学会副主任委员。擅长口腔内科疾病，口腔颌面外科肿瘤、涎腺疾病以及疑难杂症的诊断与治疗。\n" +
                        "\n1.9 临床营养科\n" +
                        "罗一\n" +
                        "教授，主任医师，国家一级营养师。长期从事疾病与营养以及肾脏病的临床和基础研究工作，基本功扎实，对各种原发性、继发性肾小球疾病和尿路感染的诊治以及慢性病的营养治疗有较丰富的临床经验。\n" +
                        "罗二\n" +
                        "副主任医师。擅长肾脏病、糖尿病、肥胖、高脂血症、肿瘤等各种慢性疾病的营养管理及营养治疗；老年、孕妇、儿童等特殊人群的营养指导；危重症患者营养风险评估与肠内肠外营养治疗。");
                break;
        }

        return view;
    }

    @Override
    public void onClick(View v) {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
