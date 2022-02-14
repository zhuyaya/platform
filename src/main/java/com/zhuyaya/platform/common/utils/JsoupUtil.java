package com.zhuyaya.platform.common.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;

/**
 * Xss 过滤工具
 *
 * @author zhengfeizhu
 * @version 1.0
 * @date 2022-02-08 14:28
 */
public class JsoupUtil {

    protected JsoupUtil() {
    }

    /**
     * 使用自带的 basicWithImages 白名单
     * 允许的标签有: a, b, blockquote, br, cite, code, dd, dl, dt, em, i, li, ol, p, pre, q, small, span,
     * strike, strong, sub, sup, u, ul, img
     * 以及a标签的href, img标签的src,align,alt, height, width, title 属性
     */
    private static final Whitelist WHITELIST = Whitelist.basicWithImages();

    /** 配置过滤参数，不对代码进行格式化 */
    private static final Document.OutputSettings OUTPUT_SETTINGS = new Document.OutputSettings().prettyPrint(false);

    static {
        /**
         * 富文本编辑时一些样式是使用style来实现的
         */
        WHITELIST.addAttributes(":all", "style");
    }

    public static String clean(String content) {
        return Jsoup.clean(content, "", WHITELIST, OUTPUT_SETTINGS);
    }
}