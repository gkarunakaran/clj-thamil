(defproject clj-thamil "0.1.0-SNAPSHOT"
  :description "A project encompassing various Thamil language-specific computing ideas"
  :url "https://github.com/echeran/clj-thamil"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]]
  ;; this config seems to work with clojure 1.5.1 but not 1.6.0, dunno why

  :jar-exclusions [#"\.cljx|\.swp|\.swo|\.DS_Store"]
  :source-paths ["src/cljx"]
  :test-paths ["target/test-classes"]
  
  :cljx {:builds [{:source-paths ["src/cljx"]
                   :output-path "target/classes"
                   :rules :clj}

                  {:source-paths ["src/cljx"]
                   :output-path "target/classes"
                   :rules :cljs}
                  
                  {:source-paths ["test/cljx"]
                   :output-path "target/test-classes"
                   :rules :clj}

                  {:source-paths ["test/cljx"]
                   :output-path "target/test-classes"
                   :rules :cljs}]}

  :cljsbuild {:test-commands {"node" ["node" :node-runner "target/testable.js"]}
              :builds [{:source-paths ["target/classes" "target/test-classes"]
                        :compiler {:output-to "target/testable.js"
                                   :optimizations :advanced
                                   :pretty-print true}}]}
  
  :hooks [cljx.hooks]

  :profiles {:dev {:plugins [[org.clojure/clojurescript "0.0-2356"]
                             [com.keminglabs/cljx "0.4.0"]
                             [lein-cljsbuild "1.0.3"]]
                   :aliases {"cleantest" ["do" "clean," "cljx" "once," "test,"
                                          "cljsbuild" "test"]
                             "deploy" ["do" "clean," "cljx" "once," "deploy" "clojars"]}}})
