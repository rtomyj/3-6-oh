package com.rtomyj.podcast.api.model

import com.rometools.modules.itunes.EntryInformationImpl
import com.rometools.rome.feed.rss.Description
import com.rometools.rome.feed.rss.Enclosure
import com.rometools.rome.feed.rss.Guid
import com.rometools.rome.feed.rss.Item
import com.rtomyj.podcast.api.helper.Constants
import java.net.URL
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*


class PodcastEpisode
{

    lateinit var episodetitle: String
    var podcastId: Int = -1
    lateinit var episodeTitle: String
    lateinit var episodeLink: URL
    lateinit var episodeDescription: String
    lateinit var episodePublicationDate: LocalDateTime
    lateinit var episodeAuthor: String
    lateinit var episodeImage: URL
    lateinit var episodeSummary: String
    var episodeGuid = Guid()

    var episodeKeywords = arrayListOf<String>()



    fun toRssItem(): Item
    {
        val item = Item()


        // Set description
        val description = Description()
        description.value = episodeDescription
        item.description = description

        val enclosure = Enclosure()
//        enclosure.length = 11779397
        enclosure.type = Constants.MEDIA_TYPE
        enclosure.url = episodeLink.toString()
        item.enclosures = Arrays.asList(enclosure)

        item.title = episodeTitle
        item.author = episodeAuthor
        item.link = episodeLink.toString()
        item.pubDate = Date.from(episodePublicationDate.toLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant())
        item.guid = episodeGuid

        val entryInformationImpl = EntryInformationImpl()
        entryInformationImpl.summary = episodeSummary
        entryInformationImpl.image = episodeImage
        entryInformationImpl.keywords = episodeKeywords.toTypedArray()
        item.modules = listOf(entryInformationImpl)

        println(item)
        return item
    }

}