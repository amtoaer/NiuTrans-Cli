package com.NiuTrans.Cli;

import java.util.Map;
import cn.hutool.http.HttpUtil;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class Request {

    public static String post(String from, String to, String src) {
        checkParam(from, to, src);
        Map<String, Object> paramMap = new HashMap<>();
        Config config = new Settings();
        paramMap.put("apikey", config.getKey());
        paramMap.put("from", from);
        paramMap.put("to", to);
        paramMap.put("src_text", src);
        return HttpUtil.post(config.getUrl(), paramMap);
    }

    private static void checkParam(String from, String to, String src) throws IllegalArgumentException {
        String tmp = "adh sq any agr knj ake ar am amu acr acu az cpb ifb ee aym ga et amk ify ojb or bdu om os tpi bdh bsn bba bch bfa ba eu be mww ber bam bqj bno ptu bg gbo map kmr bi bem is poh bnp pl bs fa pot bus cbl br bqp ti ccp cha che cv cdf tn ts mps tt da tet de tbz dv dyu tih dik duo ru djk enx gur cfm fo fr fil fj fi fy quw kg km krs jy xsm gu gub gof cnh ka kk ht hlb ko ha nl me hui gil quc ky gbi gl ca cs kac cki kab xal cak keo kn qxr kpg cjp pss kek cni cop kbh co otq crh kbo hr ksd ku kle quz la lv lo lsi lt ln ond rn ngl lg dop lb rw ro rmn rug mad gv mg mt mr mrw ml ms mdy mhr mam mk mah mni mi mo mn bn my meu mos muv ntm nhg azb quh af xh zu ne fuv nyy lnd nop no pap pck pa ata pt ps cas ny tw chr ja sv spy sm sr crs st ssx sg si mrj huv eo jiv jmc sk sl sw swp gd ceb so tg ty te ta th tpm to ctd teo tex tig lcm tmh iou tyv tr tk chq wsk wal wrs wlx war prk cy uy ve vun wol udm ur uk ppk usp uz es mbb gnw kyu lcp ssd shp he shi el hmo sid hwc haw sd hu sn syc jae jac hy ace ig ikk byr izz ii it yi hi su id jv en zyb yon yua yo pil yue vi dje dtp ifa cht zh za dz czt";
        List<String> supportLang = new ArrayList<>();
        for (String item : tmp.split(" ")) {
            supportLang.add(item);
        }
        // from/to必须为支持的语言，源语言文本长度不得大于2000
        if (!supportLang.contains(from) || !supportLang.contains(to) || src.length() == 0 || src.length() > 2000) {
            throw new IllegalArgumentException("request参数异常，请检查参数");
        }
    }
}