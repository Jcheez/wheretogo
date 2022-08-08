export interface location {
    id: number
    address ?: string
    nearestMRT ?: string
    latitude ?: number
    longitude ?: number
}

export interface attraction {
    name: string
    id : string
    cost: number
    type: string
    photoId ?: string
    locations ?: location[]
    description ?: string
    readMoreLink ?: string
}

export interface attractionPost {
    name: string
    cost: number
    photoId: string
    type: string
    locations: location[]
    description: string
    readMoreLink: string
}

export interface attractionPayload {
    id: string
    isMock: boolean
    payload: attraction
    removeFunc: Function
}

export interface dialogPayload {
    open: boolean
    onClose: Function
    attraction: attraction
    remove: Function
}

export interface GetMockAttractionResponse {
    data: attraction[]
}