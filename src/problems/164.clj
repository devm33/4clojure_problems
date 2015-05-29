(ns problems.164)

(defn __ ([dfa] (__ dfa [{:state (:start dfa) :path ""}]))
  ([dfa trails]
   (when (not-empty trails)
     (lazy-seq
       (let [cur (first trails)
             ntrans (get-in dfa [:transitions (:state cur)])
             ntrails (if ntrans
                       (concat
                         (rest trails)
                         (map
                           (fn [[t s]]
                             (hash-map :state s :path (str (:path cur) t)))
                           ntrans))
                       (rest trails))]
         (if ((:accepts dfa) (:state cur))
           (cons (:path cur) (__ dfa ntrails))
           (__ dfa ntrails)))))))

(assert (= #{"a" "ab" "abc"}
   (set (__ '{:states #{q0 q1 q2 q3}
              :alphabet #{a b c}
              :start q0
              :accepts #{q1 q2 q3}
              :transitions {q0 {a q1}
                            q1 {b q2}
                            q2 {c q3}}}))))
