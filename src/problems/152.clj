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

; check if a given square is a valid latin square
(defn latin? [sq]
  (apply = (concat (map set sq) (apply map hash-set sq))))

(assert (latin? t-sq))
(assert (not (latin? f-sq)))
(assert (not (latin? d-sq)))


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

; note: warning, concerned about double counting, may need to modify nsqmap, nsqs

(defn subsqs [vs]
  (->>
    (filter #(> 1 (count %)) vs)
    ; TODO
    ))

(def dr-sq [[1]
            [1 2 1 2]
            [2 1 2 1]
            [1 2 1 2]
            []       ]) ; { 2 2 }

(def sh-sq [[3 1 2]
            [1 2 3 1 3 4]
            [2 3 1 3]    ]) ; { 3 1, 2 2 }


