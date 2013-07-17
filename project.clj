(defproject com.ryanmcg/pedestal.jetty "0.1.0-SNAPSHOT"
  :description "http-kit adapter for Pedestal HTTP Service"
  :license {:name "The MIT License"
            :url "file://LICENSE.md"
            :distribution :repo
            :comments "Copyright 2013 Ryan McGowan"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [http-kit "2.1.1"]
                 [javax.servlet/javax.servlet-api "3.0.1"]]
  :min-lein-version "2.0.0"
  :warn-on-reflection true)
