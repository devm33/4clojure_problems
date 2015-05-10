(ns problems.117)

; for science!

; maze problem

; sample maze
(def test-maze ["#######"
                "#     #"
                "#  #  #"
                "#M # C#"
                "#######"])

; find M pos in maze

(defn find_m [maze]
  (let [r (some #(when (.contains % "M") %) maze)]
    (vector (.indexOf maze r) (.indexOf r "M"))))

(assert (= [3 1] (find_m test-maze)))

; simple bfs soln

(defn __ [maze]
  (letfn [(get_maze_at [[r c]]
            (when (and (< -1 r (count maze)) (< -1 c (count (nth maze r))))
              (nth (nth maze r) c)))]
    (loop [visited #{}
           queue (list (let [r (some #(when (.contains % "M") %) maze)]
                         (vector (.indexOf maze r) (.indexOf r "M"))))]
      (if (empty? queue) false
        (let [cur (first queue)]
          (if (= \C (get_maze_at cur)) true
            (recur
              (conj visited cur)
              (concat
                (rest queue)
                (loop [d [[1 0] [-1 0] [0 1] [0 -1]] r []]
                  (if (empty? d) r
                    (recur
                      (rest d)
                      (let [p (map + cur (first d)) v (get_maze_at p)]
                        (if (and v (not= \# v) (not (contains? visited p)))
                          (conj r p) r)))))))))))))

(assert (__ test-maze))

(assert (= true  (__ ["M   C"])))

(assert (= false (__ ["M # C"])))

(assert (= true  (__ ["#######"
              "#     #"
              "#  #  #"
              "#M # C#"
              "#######"])))

(assert (= false (__ ["########"
              "#M  #  #"
              "#   #  #"
              "# # #  #"
              "#   #  #"
              "#  #   #"
              "#  # # #"
              "#  #   #"
              "#  #  C#"
              "########"])))

(assert (= false (__ ["M     "
              "      "
              "      "
              "      "
              "    ##"
              "    #C"])))

(assert (= true  (__ ["C######"
              " #     "
              " #   # "
              " #   #M"
              "     # "])))

(assert (= true  (__ ["C# # # #"
              "        "
              "# # # # "
              "        "
              " # # # #"
              "        "
              "# # # #M"])))
