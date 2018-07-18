SELECT
       A.LOT_ID
             , A.MAT_CODE
             , B.MAT_SHORT_DESC
             , A.LINE_CODE
             , C.LINE_DESC
             , A.OPER_CODE
             , D.OPER_DESC
             , A.QTY
             , A.LAST_TRAN_CODE
             , A.LAST_TRAN_TIME
             , A.START_FLAG
             , A.END_FLAG
             , A.LOT_COMMENT
     , A.LOT_DEL_FLAG
     , A.LOT_DEL_CODE
             , E.SMALL_LABEL_ID
             , E.BOX_NO
             , E.LARGE_LABEL_ID
             , E.PALLET_NO
FROM MWIPLOTSTS A
                          LEFT OUTER JOIN MWIPOPRDEF D ON A.FACTORY_CODE = D.FACTORY_CODE AND A.OPER_CODE = D.OPER_CODE
                          LEFT OUTER JOIN MINVLBLLOT E ON A.FACTORY_CODE = E.FACTORY_CODE AND A.LOT_ID = E.LABEL_ID
             , MWIPMATDEF B
             , MWIPLINDEF C
WHERE A.FACTORY_CODE = 'MRCKR01'
             AND A.ORDER_NO = 'ORD180706001'
             AND A.FACTORY_CODE = B.FACTORY_CODE
             AND A.MAT_CODE = B.MAT_CODE
             AND A.FACTORY_CODE = C.FACTORY_CODE
             AND A.LINE_CODE = C.LINE_CODE
