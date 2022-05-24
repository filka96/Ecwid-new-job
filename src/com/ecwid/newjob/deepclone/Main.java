package com.ecwid.newjob.deepclone;

import com.ecwid.newjob.deepclone.clone.CloneUtil;
import com.ecwid.newjob.deepclone.model.Man;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        List<String> favoriteBooks = new ArrayList<>();
        favoriteBooks.add("book1");
        favoriteBooks.add("book2");
        var man = new Man("man", 123, favoriteBooks);
        System.out.println("original: " + man);
        var copy = CloneUtil.deepClone(man);
        System.out.println("   clone: " + copy);
    }
}
