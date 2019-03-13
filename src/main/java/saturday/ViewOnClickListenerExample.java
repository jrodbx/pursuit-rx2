package saturday;

public class ViewOnClickListenerExample {
  public static void main(java.lang.String[] args) throws Exception {
    View v = new View();

    v.setListener(new View.OnClickListener() {
      @Override public void onClick(View v1) {
        System.out.println("I was clicked!");
      }
    });

    v.performClick();
  }
}

class View {
  private OnClickListener onClickListener;

  public void setListener(OnClickListener listener) {
    this.onClickListener = listener;
  }

  public void performClick() {
    if (onClickListener != null) {
      onClickListener.onClick(this);
    }
  }

  interface OnClickListener {
    void onClick(View v);
  }
}

