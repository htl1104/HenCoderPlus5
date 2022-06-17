package com.example.lesson

import com.example.core.http.EntityCallback
import com.example.core.http.HttpClient
import com.example.core.utils.Utils
import com.example.lesson.entity.Lesson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class LessonPresenter {

    companion object {

        private const val LESSON_PATH: String = "lessons"
    }

    private var activity: LessonActivity

    constructor(activity: LessonActivity) {
        this.activity = activity
    }

    private var lessons = ArrayList<Lesson>()

    private val type: Type = object : TypeToken<ArrayList<Lesson>>() {}.type

    fun fetchData() {
        HttpClient.get(LESSON_PATH, type, object : EntityCallback<ArrayList<Lesson>> {
            override fun onSuccess(lessons: ArrayList<Lesson>) {
                this@LessonPresenter.lessons = lessons
                activity.runOnUiThread(Runnable { activity.showResult(lessons) })
            }

            override fun onFailure(message: String) {
                activity.runOnUiThread(Runnable { Utils.toast(message) })
            }

        })
    }

    fun showPlayback() {
        var playbackLessons = ArrayList<Lesson>()
        for (lesson in lessons) {
            if (lesson.state == Lesson.State.PLAYBACK) {
                playbackLessons.add(lesson)
            }
        }
        activity.showResult(playbackLessons)
    }

}