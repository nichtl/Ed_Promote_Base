git filter-branch --commit-filter '
        if [ "$GIT_AUTHOR_NAME" = "xujian8" ];
        then
                GIT_AUTHOR_NAME="nicht";
                GIT_AUTHOR_EMAIL="1282895175@qq.com";
                git commit-tree "$@";
        else
                git commit-tree "$@";
        fi' HEAD