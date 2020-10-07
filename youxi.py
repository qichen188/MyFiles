import pygame as pg
import sys
import random
import time

pg.init()
game_window = pg.display.set_mode((600,500))
pg.display.set_caption('接球')
window_color = (0,0,255)
ball_color = (255,165,0)
rect_color = (255,0,0)
score = 0
font = pg.font.Font(None,70)
ball_x = random.randint(20,580)
ball_y = 20
move_x = 1
move_y = 1
point = 1
count = 0
while True:
    game_window.fill(window_color)
    for event in pg.event.get():
        if event.type == pg.QUIT:
            sys.exit()
    mouse_x,mouse_y = pg.mouse.get_pos()
    pg.draw.circle(game_window,ball_color,(ball_x,ball_y),20)
    pg.draw.rect(game_window,rect_color,(mouse_x,490,100,10))
    my_text = font.render(str(score),False,(255,255,255))
    game_window.blit(my_text,(500,30))
    ball_x += move_x
    ball_y += move_y
    if ball_x <=20 or ball_x >=580:
        move_x = -move_x
    if ball_y <=20:
        move_y = -move_y
    elif mouse_x - 20 < ball_x < mouse_x + 120 and ball_y >470:
        move_y = -move_y
        score += point
        count += 1
        if count ==3:
            count = 0
            point += point
            if move_x >0:
                move_x +=1
            else:
                move_x -=1
            move_y -=1
    elif ball_y >=480 and (ball_x <= move_x -20 or ball_x >= move_x+120):
        break

    pg.display.update()
    time.sleep(0.005)