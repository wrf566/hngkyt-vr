package com.hzgktyt.vr.baselibrary;

import org.junit.Test;

import java.util.Arrays;

import static com.hzgktyt.vr.baselibrary.utils.RegexUtils.getMatches;
import static com.hzgktyt.vr.baselibrary.utils.RegexUtils.getReplaceAll;
import static com.hzgktyt.vr.baselibrary.utils.RegexUtils.getReplaceFirst;
import static com.hzgktyt.vr.baselibrary.utils.RegexUtils.getSplits;
import static com.hzgktyt.vr.baselibrary.utils.RegexUtils.isDate;
import static com.hzgktyt.vr.baselibrary.utils.RegexUtils.isEmail;
import static com.hzgktyt.vr.baselibrary.utils.RegexUtils.isIDCard18;
import static com.hzgktyt.vr.baselibrary.utils.RegexUtils.isIP;
import static com.hzgktyt.vr.baselibrary.utils.RegexUtils.isMatch;
import static com.hzgktyt.vr.baselibrary.utils.RegexUtils.isMobileExact;
import static com.hzgktyt.vr.baselibrary.utils.RegexUtils.isMobileSimple;
import static com.hzgktyt.vr.baselibrary.utils.RegexUtils.isTel;
import static com.hzgktyt.vr.baselibrary.utils.RegexUtils.isURL;
import static com.hzgktyt.vr.baselibrary.utils.RegexUtils.isUsername;
import static com.hzgktyt.vr.baselibrary.utils.RegexUtils.isZh;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
/**
 RegularUtils单元测试
 **/
public class RegexUtilsTest {

    @Test
    public void testIsMobileSimple() throws Exception {
        assertTrue(isMobileSimple("11111111111"));
    }

    @Test
    public void testIsMobileExact() throws Exception {
        assertFalse(isMobileExact("11111111111"));
        assertTrue(isMobileExact("13888880000"));
    }

    @Test
    public void testIsTel() throws Exception {
        assertTrue(isTel("033-88888888"));
        assertTrue(isTel("033-7777777"));
        assertTrue(isTel("0444-88888888"));
        assertTrue(isTel("0444-7777777"));
        assertTrue(isTel("033 88888888"));
        assertTrue(isTel("033 7777777"));
        assertTrue(isTel("0444 88888888"));
        assertTrue(isTel("0444 7777777"));
        assertTrue(isTel("03388888888"));
        assertTrue(isTel("0337777777"));
        assertTrue(isTel("044488888888"));
        assertTrue(isTel("04447777777"));

        assertFalse(isTel("133-88888888"));
        assertFalse(isTel("033-666666"));
        assertFalse(isTel("0444-999999999"));
    }

    @Test
    public void testIsIDCard() throws Exception {
        assertTrue(isIDCard18("33698418400112523x"));
        assertTrue(isIDCard18("336984184001125233"));
        assertFalse(isIDCard18("336984184021125233"));
    }

    @Test
    public void testIsEmail() throws Exception {
        assertTrue(isEmail("wrf@qq.com"));
        assertFalse(isEmail("wrf@qq"));
    }

    @Test
    public void testIsURL() throws Exception {
        assertTrue(isURL("http://wrf.com"));
        assertFalse(isURL("http://wrf"));
    }

    @Test
    public void testIsChz() throws Exception {
        assertTrue(isZh("我"));
        assertFalse(isZh("wo"));
    }

    @Test
    public void testIsUsername() throws Exception {
        assertTrue(isUsername("小明233333"));
        assertFalse(isUsername("小明"));
        assertFalse(isUsername("小明233333_"));
    }

    @Test
    public void testIsDate() throws Exception {
        assertTrue(isDate("2016-08-16"));
        assertTrue(isDate("2016-02-29"));
        assertFalse(isDate("2015-02-29"));
        assertFalse(isDate("2016-8-16"));
    }

    @Test
    public void testIsIP() throws Exception {
        assertTrue(isIP("255.255.255.0"));
        assertFalse(isIP("256.255.255.0"));
    }

    @Test
    public void testIsMatch() throws Exception {
        assertTrue(isMatch("\\d?", "1"));
        assertFalse(isMatch("\\d?", "a"));
    }

    @Test
    public void testGetMatches() throws Exception {
        // 贪婪
        System.out.println(getMatches("b.*j", "wrf wrf"));
        // 懒惰
        System.out.println(getMatches("b.*?j", "wrf wrf"));
    }

    @Test
    public void testGetSplits() throws Exception {
        System.out.println(Arrays.asList(getSplits("1 2 3", " ")));
    }

    @Test
    public void testGetReplace() throws Exception {
        System.out.println(getReplaceFirst("1 2 3", " ", ", "));
    }

    @Test
    public void testGetReplaceAll() throws Exception {
        System.out.println(getReplaceAll("1 2 3", " ", ", "));
    }
}