import React from 'react'
import { attraction, attractionPayload } from '../interfaces'
import { Button, Card, CardActions, CardContent, Link, Typography } from '@mui/material'
import { Box } from '@mui/system'


export default function AttractionCard(props: attractionPayload) {

    const mockAttractionContent = (attraction: attraction) => {
        return (
            <Card variant='outlined'>
                <CardContent>
                    <Typography variant='h4' component="div">
                        {attraction.name}
                    </Typography>
                    <Typography gutterBottom variant="body2" color="text.secondary">
                        Estimated Cost: ${attraction.cost}
                    </Typography>
                    <Typography gutterBottom variant="body2" color="text.secondary">
                        Type: {attraction.type}
                    </Typography>
                </CardContent>
                <CardActions>
                    <Button size="small">Make Legit</Button>
                    <Button size="small">Remove</Button>
                </CardActions>
            </Card>
        )
    }

    const realAttractionContent = (attraction: attraction) => {
        return (
            <Card>
                <CardContent>
                    <Typography variant='h4' component="div">
                        {attraction.name}
                    </Typography>
                    <Typography gutterBottom variant="body2" color="text.secondary">
                        Estimated Cost: ${attraction.cost}
                    </Typography>
                    <Typography gutterBottom variant="body2" color="text.secondary">
                        Description: {attraction.description}
                    </Typography>
                    <Typography gutterBottom variant="body2" color="text.secondary">
                        Type: {attraction.type}
                    </Typography>
                    <Typography gutterBottom variant="body2" color="text.secondary">
                        <Link href={props.payload.readMore}>Read More</Link>
                    </Typography>
                </CardContent>
                <CardActions>
                    <Button size="small">Edit</Button>
                    <Button size="small">Remove</Button>
                </CardActions>
            </Card>
        )
    }

    return (
        <Box sx={{ maxWidth: 400 }}>
            {props.isMock ? mockAttractionContent(props.payload) : realAttractionContent(props.payload)}
        </Box>
    )
}
