package com.github.longqiany.fastdev.core.io;

import com.github.longqiany.fastdev.core.utils.StringUtils;

/**
 * Created by zzz on 12/8/15.
 */
public class SafaFileNameGenerator implements NameGenerator {
    @Override
    public String generate(String s) {
        return StringUtils.toSafeFileName(s);
    }
}
