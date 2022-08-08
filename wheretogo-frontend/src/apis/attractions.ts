import axios from 'axios';
import { attraction, attractionPost } from '../interfaces';

const ATTRACTIONSURL = "http://where-to-go-355603.as.r.appspot.com/api/attractions"

export const getAllAttractions = ():Promise<attraction[]> => {
    const promise = axios.get<attraction[]>(ATTRACTIONSURL).then(res => {
        return res.data
    })
    return promise
}

export const deleteAttraction = async (id: string):Promise<number> => {
    const promise = await axios.delete(ATTRACTIONSURL + "/" + id)
    return promise.status
}

export const postAttraction = (attraction: attractionPost) => {
    const promise = axios.post(ATTRACTIONSURL, attraction).then(res => {
        return res.status
    })
    return promise
}