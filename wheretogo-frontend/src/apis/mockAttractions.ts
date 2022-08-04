import axios from 'axios';
import { attraction } from '../interfaces';

const MOCKATTRACTIONSURL = "https://where-to-go-355603.as.r.appspot.com/api/mock/attractions"

export const getMockAttractions = async ():Promise<attraction[]> => {
    const promise = await axios.get<attraction[]>(MOCKATTRACTIONSURL).then(mock => {
        return mock.data
    })
    return promise
}