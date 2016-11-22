package com.example.mrlu.a2048;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;
import android.widget.VideoView;

import static android.R.attr.x;


/**
 * Created by Mr Lu on 2016/11/17.
 */

public class GameView extends GridLayout {


    ///

    public GameView(Context context) {
        super(context);
        initGame();
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initGame();
    }

    public GameView(Context context, AttributeSet arrs, int defStyle) {
        super(context);
        initGame();
    }


    private void initGame() {
        setColumnCount(4);
        setBackgroundColor(0xffffcccc);
        setOnTouchListener(new OnTouchListener() {
            private float startX, startY;
            private float offsetX, offsetY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startX = event.getX();
                        startY = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        gameOver();

                        offsetX = event.getX() - startX;
                        offsetY = event.getY() - startY;
                        if (Math.abs(offsetX) > Math.abs(offsetY)) {
                            if (Math.abs(offsetX) < -3) {
                                moveLeft();
                                System.out.println("----左");


                            } else if (offsetX > 3) {
                                moveRight();
                                System.out.println("----右");
                            }
                        } else {
                            if (offsetY < -3) {
                                moveUp();
                                System.out.println("----上");
                            } else if (offsetY > 3) {
                                moveDown();
                                System.out.println("----下");
                            }
                        }
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    //定义用户向右滑动响应函数
    private void moveRight() {
        boolean flage = false;
        for (int y = 0; y < 4; y++) {
            for (int x = 3; x >= 0; x--) {
                for (int x1 = x - 1; x1 >= 0; x1--) {
                    //当同一行为空时，不需处理
                    if (cards[x1][y].getNumber() > 0) {
                        if (cards[x][y].getNumber() < 2) {
                            //将前一张卡片的值移动到当前卡片
                            cards[x][y].setNumber(cards[x1][y].getNumber());
                            cards[x1][y].setNumber(0);
                            x++;
                            flage = true;
                            score += 2;

                        } else if (cards[x][y].getNumber() == cards[x1][y].getNumber()) {
                            cards[x][y].setNumber(cards[x][y].getNumber() * 2);
                            score += cards[x][y].getNumber();
                            cards[x1][y].setNumber(0);
                            flage = true;
                        }
                        break;
                    }
                }
            }
        }

        if (flage) {
            createRandomCard();
        }
    }


    private void moveLeft() {
        boolean flage = false;
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x >= 4; x++) {
                for (int x1 = x + 1; x1 < 4; x1++) {
                    //当同一行为空时，不需处理
                    if (cards[x1][y].getNumber() > 0) {
                        if (cards[x][y].getNumber() < 2) {
                            //将前一张卡片的值移动到当前卡片
                            cards[x][y].setNumber(cards[x1][y].getNumber());
                            cards[x1][y].setNumber(0);
                            x--;
                            flage = true;
                            score += 2;

                        } else if (cards[x][y].getNumber() == cards[x1][y].getNumber()) {
                            cards[x][y].setNumber(cards[x][y].getNumber() * 2);
                            score += cards[x][y].getNumber();
                            cards[x1][y].setNumber(0);
                            flage = true;
                        }
                        break;
                    }
                }
            }
        }

        if (flage) {
            createRandomCard();
        }
    }

    private void moveDown() {
        boolean flage = false;
        for (int x = 0; x < 4; x++) {
            for (int y = 3; y >= 0; y--) {
                for (int y1 = y - 1; y1 >= 0; y1--) {
                    //当同一行为空时，不需处理
                    if (cards[x][y1].getNumber() > 0) {
                        if (cards[x][y].getNumber() < 2) {
                            //将前一张卡片的值移动到当前卡片
                            cards[x][y].setNumber(cards[x][y1].getNumber());
                            cards[x][y1].setNumber(0);
                            y++;
                            flage = true;
                            score += 2;

                        } else if (cards[x][y].getNumber() == cards[x][y1].getNumber()) {
                            cards[x][y].setNumber(cards[x][y].getNumber() * 2);
                            score += cards[x][y].getNumber();
                            cards[x][y1].setNumber(0);
                            flage = true;
                        }
                        break;
                    }
                }
            }
        }

        if (flage) {
            createRandomCard();
        }
    }

    private void moveUp() {
        boolean flage = false;
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                for (int y1 = y + 1; y1 < 4; y1++) {
                    //当同一行为空时，不需处理
                    if (cards[x][y1].getNumber() > 0) {
                        if (cards[x][y].getNumber() < 2) {
                            //将前一张卡片的值移动到当前卡片
                            cards[x][y].setNumber(cards[x][y1].getNumber());
                            cards[x][y1].setNumber(0);
                            y--;
                            flage = true;
                            score += 2;

                        } else if (cards[x][y].getNumber() == cards[x][y1].getNumber()) {
                            cards[x][y].setNumber(cards[x][y].getNumber() * 2);
                            score += cards[x][y].getNumber();
                            cards[x][y1].setNumber(0);
                            flage = true;
                        }
                        break;
                    }
                }
            }
        }

        if (flage) {
            createRandomCard();
        }
    }

    private void gameOver() {
        boolean OverGame ();
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; y++) {
                if (cards[x][y].getNumber() <= 0)||
                (x > 0 && cards[x][y].getNumbers() ==
                        cards[x - 1][y].getNumbers()) ||
                        (x < 3 && cards[x][y].getNumbers() ==
                                cards[x + 1][y].getNumbers()) ||
                        (y > 0 && cards[x][y].getNumbers() ==
                                cards[x][y - 1].getNumbers()) ||
                        (y < 3 && cards[x][y].getNumbers() ==
                                cards[x][y + 1].getNumbers()) {
                    OverGame = false;
                }


            }
        }
        if (OverGame) {
            new AlertDialog.Builder(getContext()).
                    setTitle("hi").setMessage("again").
                    setPositiveButton("yes", new AlertDialog.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            gameStart();
                            score = 0;

                        }
                    }).setNegativeButton("No", null).show();

        }
    }

    private void addCard(int width, int height) {
        Card c;
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                c = new Card(getContext());

                cards[x][y] = c;
                c.setNumbers(0);
                addView(c, width, height);

            }
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        int width = (w - 10) / 4;
        addCard(width, width);
        gameStart();
    }

    private void createRandomCard() {
        emptyCards.cleaar();
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                if (cards[x][y].getNumbers() < 2) {
                    Point point = new Point(x, y);

                    emptyCards.add(point);

                }
            }
        }

        int selat = rd.nextInt(emptyCards.get(selat));
        emptyCards.remove(selat);
        int number = 0;
        if (rd.nextInt(10) > 4) {
            number = 4;

        } else {
            number = 2;

        }

        cards[p.x][p.y].setNumber(number);
    }

    public void gameStart(){
        for (int y = 0; y < 4; y++){
            for (int x = 0; x < 4; x++){
                cards[x][y].setNumber(0);
            }
        }
        createRandomCard();
        createRandomCard();
    }


}


