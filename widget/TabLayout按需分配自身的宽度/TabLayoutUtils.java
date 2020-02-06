public class TabLayoutUtils {

    private static final int TITLE_COLOR_NORMAL = Color.parseColor("#ff666666");
    private static final int TITLE_COLOR_SELECTED = Color.parseColor("#fff3543c");

    // TabLayout的两种模式：fixed，scrolled
    // fixed平分屏幕宽度给每个Tab，scrolled按需分配，Tab少时不确保占满一屏
    // 该方法：使用scrolled模式，按需分配给各个Tab后，将剩下的宽度再次均分
    // 注意在TabLayout.setupWithViewPager后调用
    public static void expandTabWidth(TabLayout tabLayout) {
        if (tabLayout == null || tabLayout.getTabCount() <= 0) return;
        Context context = tabLayout.getContext();
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        // 先将Tab View替换成自定义的View
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setCustomView(getTabView(context, tab.getText()));
        }

        // 制定测量规则 参数表示size + mode
        int width = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int height = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        tabLayout.measure(width, height);

        // 计算每个Tab需分配的额外宽度
        int extra = (context.getResources().getDisplayMetrics().widthPixels - tabLayout.getMeasuredWidth()) / tabLayout.getTabCount();
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            ViewGroup.LayoutParams lp = tab.getCustomView().getLayoutParams();
            lp.width = tab.getCustomView().getMeasuredWidth() + extra;
            tab.getCustomView().setLayoutParams(lp);
        }

        selectTab(tabLayout.getTabAt(0));
    }

    private static View getTabView(Context context, CharSequence title) {
        // R.layout.tab中包含一个TextView（Title），和一个View（Indicator）
        View tabView = View.inflate(context, R.layout.tab, null);
        TextView tv = tabView.findViewById(R.id.tab_tv);
        tv.setText(title);
        return tabView;
    }

    // 设置Tab为选中状态
    public static void selectTab(TabLayout.Tab tab) {
        changeTabState(tab, true);
    }

    // 设置Tab未选中状态
    public static void unselectTab(TabLayout.Tab tab) {
        changeTabState(tab, false);
    }

    private static void changeTabState(TabLayout.Tab tab, boolean selected) {
        if (tab == null || tab.getCustomView() == null) return;
        TextView tv = tab.getCustomView().findViewById(R.id.tab_tv);
        View indicator = tab.getCustomView().findViewById(R.id.tab_indicator);
        tv.setTextColor(selected ? TITLE_COLOR_SELECTED : TITLE_COLOR_NORMAL);
        tv.setTypeface(Typeface.defaultFromStyle(selected ? Typeface.BOLD : Typeface.NORMAL));
        indicator.setVisibility(selected ? View.VISIBLE : View.INVISIBLE);
    }
}
