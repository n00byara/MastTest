package ru.n00byara.masttest.components.clientapi.pojo;

import java.util.List;

public class Quotes {
   public List<Quote> quotes;

    public class Quote {
        public int id;
        public String quote;
        public String author;
    }
}
