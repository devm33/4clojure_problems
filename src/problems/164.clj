(ns problems.164)

(defn __ [dfa]
  (letfn
    [(map-trans [cur trans]
       (when trans
         (map (fn [[t s]] {:state s :path (str (:path cur) t)}) trans)))
     (enq [trails]
       (when (not-empty trails)
         (lazy-seq
           (let [cur (first trails)
                 next-trans (get-in dfa [:transitions (:state cur)])
                 next-trails (concat (rest trails) (map-trans cur next-trans))]
             (if ((:accepts dfa) (:state cur))
               (cons (:path cur) (enq next-trails))
               (enq next-trails))))))]
    (enq [{:state (:start dfa) :path ""}])))

(assert (= #{"a" "ab" "abc"}
           (set (__ '{:states #{q0 q1 q2 q3}
                      :alphabet #{a b c}
                      :start q0
                      :accepts #{q1 q2 q3}
                      :transitions {q0 {a q1}
                                    q1 {b q2}
                                    q2 {c q3}}}))))
