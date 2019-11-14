package com.odont.odont.transformer;

public interface Transformer<FROM, TO> {
    TO transform(FROM chat);
}
