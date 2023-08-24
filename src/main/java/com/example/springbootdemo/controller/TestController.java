package com.example.springbootdemo.controller;

public class TestController {

    public ResultBean deleteById(String id, String userId) {
        WxCouponInfo wxCouponInfo = this.wxCouponInfoMapper.selectByPrimaryKey(id);
        if (wxCouponInfo == null || wxCouponInfo.getStatus() == CommonConstants.MODEL_STATUS_DELETE) {
            return ResultBean.error("消费券已被删除，操作失败");
        }
        if (0 < wxCouponInfo.getActualTotalNum()) {
            return ResultBean.error("已有领取记录，无法删除");
        }

        List<String> activityNameList = this.wxCouponActivityRelatedCouponMapper.getCountByCouponInfoId(id);
        if(null != activityNameList && activityNameList.size() > 0){

            return ResultBean.error("消费券已挂在"+activityNameList.toString()+ "活动上，请先在对应活动里下架该券再删除");
        }
        wxCouponInfo.setStatus(CommonConstants.MODEL_STATUS_DELETE);
        wxCouponInfo.setUpdateUserId(userId);
        wxCouponInfo.setUpdateTime(new Date());
        this.wxCouponInfoMapper.updateByPrimaryKey(wxCouponInfo);

        //删除活动关联消费券表记录
        this.wxCouponActivityRelatedCouponMapper.updByCouponInfoId(id);
        return ResultBean.ok("操作成功");
    }



    public class WxCouponStockBizTest {
        //桩注入
        @Mock
        private static WxCouponInfoMapper wxCouponInfoMapper;

        @Mock
        private static WxCouponActivityRelatedCouponMapper wxCouponActivityRelatedCouponMapper;

        @InjectMocks
        private static WechatCouponStockBiz wechatCouponStockBiz;

        private static Date date;
        private static String uid;
        private static WxCouponInfo wxCouponInfo;

        static {
            uid = "123";
            wxCouponInfo = new WxCouponInfo();
            wxCouponInfo.setId("123");
            wxCouponInfo.setWechatMchid("123");
            wxCouponInfo.setWechatMchName("123");
            wxCouponInfo.setCouponStockId("123");
            wxCouponInfo.setActualTotalNum(0);
            wxCouponInfo.setPreDailyNum(123);
            wxCouponInfo.setServiceTarget(2);
            wxCouponInfo.setGrandStartTime(date);
            wxCouponInfo.setGrandEndTime(date);
            wxCouponInfo.setPerUserDailyNum(123);
            wxCouponInfo.setWriteOffNum(123);
            wxCouponInfo.setCreateUserId("123");
            wxCouponInfo.setCreateTime(date);
            wxCouponInfo.setStatus(0);

            @Before
            public void setUp() throws Exception {
                // 初始化mock对象
                MockitoAnnotations.initMocks(this);

                // 开始mock静态打桩
                PowerMockito.mockStatic(Date.class);
                PowerMockito.whenNew(Date.class).withNoArguments().thenReturn(date);
                PowerMockito.mockStatic(IDUtils.class);
                PowerMockito.when(IDUtils.nextId()).thenReturn("123");
            }

            @Test
            public void deleteByIdTest() throws Exception {
                List<String> list = new ArrayList<>();

                when(wxCouponInfoMapper.selectByPrimaryKey(uid)).thenReturn(wxCouponInfo);
                when(wxCouponActivityRelatedCouponMapper.getCountByCouponInfoId(uid)).thenReturn(list);
                when(wxCouponInfoMapper.selectByPrimaryKey("123")).thenReturn(wxCouponInfo);
                List<String> activityNameList = new ArrayList<String>();
                when(wxCouponActivityRelatedCouponMapper.getCountByCouponInfoId("123")).thenReturn(activityNameList);
                when(wxCouponActivityRelatedCouponMapper.updByCouponInfoId("123")).thenReturn(1);
                wxCouponInfo.setUpdateUserId(uid);
                wxCouponInfo.setUpdateTime(date);
                when(wxCouponInfoMapper.updateByPrimaryKey(wxCouponInfo)).thenReturn(1);
                when(wxCouponActivityRelatedCouponMapper.updByCouponInfoId(uid)).thenReturn(1);
                ResultBean result = wechatCouponStockBiz.deleteById(uid,uid);
                assertEquals(1, result.get("code"));
            }
        }

}
