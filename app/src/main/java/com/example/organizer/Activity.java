package com.example.organizer;

public class Activity{

    private String id;
    private String time;
    private String title;
    private String content;

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return this.id;
    }

    public void setTime(String time)
    {
        this.time = time;
    }

    public void setTitle(String title)
    {
        this.title = title;

    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public String getTime()
    {
        return this.time;
    }

    public String getTitle()
    {
        return this.title;
    }

    public String getContent()
    {
        return this.content;
    }

}
