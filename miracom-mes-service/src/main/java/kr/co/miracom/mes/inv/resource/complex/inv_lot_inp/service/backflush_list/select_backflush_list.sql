SELECT
        A.CHILD_MAT_CODE
      , D.MAT_SHORT_DESC AS MAT_DESC
      , SUM(C.QTY) AS SUM_QTY
  FROM  MWIPORDBOM A INNER JOIN  MWIPMATDEF D
                             ON  A.FACTORY_CODE        = D.FACTORY_CODE
                            AND  A.CHILD_MAT_CODE      = D.MAT_CODE
                            AND  D.DEDUCTION_TYPE      = 'B'
                     INNER JOIN  MWIPINVLOD B
                             ON  A.FACTORY_CODE        = B.FACTORY_CODE
                            AND  A.CHILD_MAT_CODE      = B.MAT_CODE
                     INNER JOIN  MINVLOTSTS C
                             ON  B.FACTORY_CODE        = C.FACTORY_CODE
                            AND  B.INV_LOT_ID          = C.INV_LOT_ID

 WHERE  A.FACTORY_CODE    = :factoryCode
   AND  A.ORDER_NO        = :orderNo
   AND  B.LINE_CODE       = :lineCode
 GROUP BY A.CHILD_MAT_CODE
        , D.MAT_SHORT_DESC