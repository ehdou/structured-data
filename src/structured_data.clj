(ns structured-data)

(defn do-a-thing [x]
  (let [sum (+ x x)]
    (Math/pow sum sum)))

(defn spiff [v]
  (+ (get v 0) (get v 2)))

(defn cutify [v]
  (conj v "<3"))

(defn spiff-destructuring [[a b c]]
  (+ a c))

(defn point [x y]
  [x y])

(defn rectangle [bottom-left top-right]
  [bottom-left top-right])

(defn width [[[x1 y1] [x2 y2]]]
  (Math/abs (- x1 x2)))

(defn height [[[x1 y1] [x2 y2]]]
  (Math/abs (- y1 y2)))

(defn square? [rectangle]
  (== (width rectangle) (height rectangle)))

(defn area [rectangle]
  (* (width rectangle) (height rectangle)))

(defn contains-point? [[[x1 y1] [x2 y2]] [x y]]
  (and (<= x1 x x2) (<= y1 y y2)))

(defn contains-rectangle? [outer [p1 p2]]
  (and (contains-point? outer p1) (contains-point? outer p2)))

(defn title-length [book]
  (count (:title book)))

(defn author-count [book]
  (count (:authors book)))

(defn multiple-authors? [book]
  (> (author-count book) 1))

(defn add-author [book new-author]
  (let [authors (conj (:authors book) new-author)]
    (assoc book :authors authors)))

(defn alive? [author]
  (not (contains? author :death-year)))

(defn element-lengths [collection]
  (map count collection))

(defn second-elements [collection]
  (let [getsecond (fn [c] (get c 1))]
    (map getsecond collection)))

(defn titles [books]
  (map :title books))

(defn monotonic? [a-seq]
  (or(apply <= a-seq) (apply >= a-seq)))

(defn stars [n]
  (apply str (repeat n \*)))

(defn toggle [a-set elem]
  (if (contains? a-set elem) (disj a-set elem) (conj a-set elem)))

(defn contains-duplicates? [a-seq]
  (not (== (count a-seq) (count (set a-seq)))))

(defn old-book->new-book [book]
  (assoc book :authors (set (:authors book))))

(defn has-author? [book author]
  (contains? (:authors book) author))

(defn authors [books]
  (apply clojure.set/union (map :authors books)))

(defn all-author-names [books]
  (set (map :name (authors books))))

(defn author->string [author]
  (str (:name author)
    (cond
      (contains? author :death-year) (str " (" (:birth-year author) " - " (:death-year author) ")")
      (contains? author :birth-year) (str " (" (:birth-year author) " - )")
      :else                          "")))

(defn authors->string [authors]
  (apply str (interpose ", " (map author->string authors))))

(defn book->string [book]
  (str (:title book) ", written by " (authors->string (:authors book))))

(defn books->string [books]
  (let [nb-books (count books)]
    (cond
      (== nb-books 0) "No books."
      (== nb-books 1) (str "1 book. " (apply str (map book->string books)) ".")
      :else           (str nb-books " books. " (apply str (interpose ". " (map book->string books))) "."))))

(defn books-by-author [author books]
  (filter (fn [book] (has-author? book author)) books))

(defn author-by-name [name authors]
  (first (filter (fn [author] (= (:name author) name)) authors)))

(defn living-authors [authors]
  (filter alive? authors))

(defn has-a-living-author? [book]
  (not (empty? (living-authors (:authors book)))))

(defn books-by-living-authors [books]
  (filter has-a-living-author? books))

; %________%
