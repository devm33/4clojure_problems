(ns problems.152)

(def t-sq '[[A B C]
            [B C A]
            [C A B]])

(def f-sq '[[A B C]
            [B C A]
            [C A C]])

(def d-sq '[[A B C]
            [B D A]
            [C A B]])

(def ft-sq '[[A B C D]
             [B A D C]
             [D C B A]
             [C D A B]])

(def sm-sq '[[1 2 1]
             [2 1 2]
             [1 2 1]])

; check if a given square is a valid latin square
(defn old-latin? [sq]
  (and (apply = (concat (map set sq) (apply map hash-set sq)))
       (= (count sq) (count (set (first sq))))))

; different approach not using set
(defn latin? [sq]
  (apply = (sort (first sq))
         (map (comp distinct sort sort) (concat sq (apply map vector sq)))))

(assert (latin? t-sq))
(assert (not (latin? f-sq)))
(assert (not (latin? d-sq)))
(assert (not (latin? sm-sq)))


; return the number of latin subsquares of size n
(defn nsqs [sq n]
  (if (= n (count sq))
    (if (latin? sq) 1 0)
    (count
      (for [r (range (inc (- (count sq) n)))
            c (range (inc (- (count sq) n)))
            :let [m (map #(take n (drop c %)) (take n (drop r sq)))]
            :when (latin? m)] 1))))

(assert (= 1 (nsqs ft-sq 4)))
(assert (= 4 (nsqs ft-sq 2)))

; return a mapping of sizes to counts of latin subsquares
(defn nsqmap [sq]
  (->>
    (range 2 (inc (count sq)))
    (map #(vector % (nsqs sq %)))
    (filter #(-> % second (not= 0)))
    (into {})))

(assert (= {4 1 2 4} (nsqmap ft-sq)))

; return all sub-squares of vectors (accounting for shifting)

(defn __ [vs]
  (let [rs (filter #(< 1 (count %)) vs)]
    (reduce
      (fn [m n]
        (let
          [c-n
           (->>
             (for [r (range (inc (- (count rs) n)))
                   :let [rows (take n (drop r rs))
                         max-col-sub (apply max (map count rows))]
                   :when (every? #(<= n (count %)) rows)
                   c (range (inc (- (apply max (map count rows)) n)))]
               (reduce
                 (fn [rets row]
                   (mapcat
                     #(for [coff (range (inc (- max-col-sub (count row))))
                            :when (< -1 (- c coff) (inc (- (count row) n)))
                            :let [subrow (take n (drop (- c coff) row))]]
                        (concat % (vector subrow))) rets))
                 '[[]] rows))
             (apply concat)
             (filter #(apply = (sort (first %))
                             (map (comp distinct sort sort)
                                  (concat % (apply map vector %)))))
             distinct count)]
          (if (< 0 c-n) (assoc m n c-n) m)))
      {} (range 2 (inc (apply max (map count vs)))))))


(assert (= (__ '[[A B C D]
                 [A C D B]
                 [B A D C]
                 [D C A B]])
           {}))

(assert (= (__ '[[A B C D E F]
                 [B C D E F A]
                 [C D E F A B]
                 [D E F A B C]
                 [E F A B C D]
                 [F A B C D E]])
           {6 1}))

(assert (= (__ '[[A B C D]
                 [B A D C]
                 [D C B A]
                 [C D A B]])
           {4 1, 2 4}))

(assert (= (__ '[[B D A C B]
                 [D A B C A]
                 [A B C A B]
                 [B C A B C]
                 [A D B C A]])
           {3 3}))

(assert (= (__ [  [2 4 6 3]
                [3 4 6 2]
                [6 2 4]  ])
           {}))

(assert (= (__ [[1]
                [1 2 1 2]
                [2 1 2 1]
                [1 2 1 2]
                []       ])
           {2 2}))

(assert (= (__ [[3 1 2]
                [1 2 3 1 3 4]
                [2 3 1 3]    ])
           {3 1, 2 2}))

(println (__ [[8 6 7 3 2 5 1 4]
              [6 8 3 7]
              [7 3 8 6]
              [3 7 6 8 1 4 5 2]
              [1 8 5 2 4]
              [8 1 2 4 5]]))

(assert (= (__ [[8 6 7 3 2 5 1 4]
                [6 8 3 7]
                [7 3 8 6]
                [3 7 6 8 1 4 5 2]
                [1 8 5 2 4]
                [8 1 2 4 5]])
           {4 1, 3 1, 2 7}))
