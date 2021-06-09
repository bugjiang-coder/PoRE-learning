char maze[256] = {
    3, 9, 6, 9, 6, 6, 6, 3, 2, 0, 4, 7, 0, 0, 9, 2,
    3, 9, 6, 8, 7, 6, 6, 0, 4, 3, 8, 3, 9, 0, 1, 0,
    4, 5, 8, 7, 0, 5, 3, 8, 8, 1, 2, 3, 4, 0, 9, 1,
    0, 0, 9, 6, 7, 4, 3, 2, 1, 0, 9, 5, 5, 1, 9, 2,
    2, 3, 4, 5, 4, 3, 8, 4, 8, 0, 4, 5, 6, 3, 1, 9,
    1, 3, 8, 4, 4, 6, 3, 1, 9, 8, 3, 5, 7, 3, 4, 3,
    0, 0, 8, 3, 4, 5, 9, 1, 4, 9, 5, 9, 8, 3, 4, 3,
    1, 2, 3, 2, 9, 7, 0, 3, 4, 5, 7, 9, 9, 7, 5, 1,
    7, 9, 4, 2, 0, 5, 4, 3, 2, 7, 6, 9, 8, 5, 3, 0,
    0, 7, 5, 8, 7, 6, 3, 0, 1, 0, 1, 2, 7, 9, 7, 3,
    1, 8, 6, 7, 0, 1, 2, 3, 4, 7, 4, 3, 6, 5, 5, 7,
    0, 9, 4, 4, 2, 0, 1, 8, 1, 9, 5, 4, 5, 0, 4, 8,
    6, 8, 7, 3, 1, 1, 0, 1, 2, 5, 4, 5, 6, 1, 8, 1,
    4, 3, 6, 5, 4, 2, 7, 5, 9, 2, 2, 6, 7, 8, 1, 6,
    0, 2, 3, 0, 3, 9, 0, 2, 8, 3, 1, 3, 1, 9, 8, 7,
    9, 9, 7, 6, 7, 0, 6, 7, 1, 7, 6, 1, 8, 0, 4, 6};

int judge(char *input)
{
    char *m = maze;
    int i = 0;
    int j = 3;
    int top = m[(i << 4) + j];
    int index = 0;
    int pre;
    int now;
    int gap;
    int pre_is_up = 0;

    while (1)
    {
        if (i < 0 || j < 0 || i > 0xf || j > 0xf)
        {
            break;
        }
        int pre = m[(i << 4) + j];
        switch (input[index++])
        {
        case 'w':
            i--;
            break;
        case 'a':
            j--;
            break;
        case 's':
            i++;
            break;
        case 'd':
            j++;
            break;
        default:
//            printf("wrong input\n");
            return 0;
        }

        now = m[(i << 4) + j];
//        printf("%d",now);

        gap = now - pre;
        if (gap == 1)
        {
            if (now == top)
            {
//                printf("too high\n");
                break;

            }
            pre_is_up = 1;
        }
        else if (gap == -1)
        {
            if (pre_is_up)
            {
                top = now + 1;
            }

            pre_is_up = 0;
        }
        else
        {
//            printf("gap wrong\n");
            break;
        }

        if (i == 0xd && j == 0xb)
        {
            return 1;
        }
    }
    return 0;
}

/*
int main()
{
    int flag = 0;
    // "ssssaaasssddsssdwddwdddsdddssss"
    flag = judge("ssssaaasssddsssdwddwdddsdddssss");
        // "sssssssasssdwddwdddsdddssss");
    if (flag)
    {
        printf("yes");
    }
    else
    {
        printf("no");
    }
    return 0;
}*/
